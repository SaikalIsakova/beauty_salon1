package kg.megacom.beauty_salon.service.impl;

import kg.megacom.beauty_salon.dao.OrderRep;
import kg.megacom.beauty_salon.exceptions.ClientUnauthorizedEx;
import kg.megacom.beauty_salon.exceptions.NotFoundEx;
import kg.megacom.beauty_salon.mappers.OrderMapper;
import kg.megacom.beauty_salon.models.dto.ClientDto;
import kg.megacom.beauty_salon.models.dto.MasterDto;
import kg.megacom.beauty_salon.models.dto.OrderDto;
import kg.megacom.beauty_salon.models.dto.ScheduleDto;
import kg.megacom.beauty_salon.models.enums.OrderStatusEnum;
import kg.megacom.beauty_salon.models.enums.WorkDayEnum;
import kg.megacom.beauty_salon.models.request.OrderRequest;
import kg.megacom.beauty_salon.response.Response;
import kg.megacom.beauty_salon.service.*;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {


    OrderMapper mapper = OrderMapper.INSTANCE;

    private final OrderRep rep;
    private final ClientService clientService;
    private final MasterService masterService;
    private final ScheduleService scheduleService;
    private final EmailSenderService emailSenderService;

    public OrderServiceImpl(OrderRep rep, ClientService clientService, MasterService masterService, ScheduleService scheduleService, EmailSenderService emailSenderService) {
        this.rep = rep;
        this.clientService = clientService;
        this.masterService = masterService;
        this.scheduleService = scheduleService;
        this.emailSenderService = emailSenderService;
    }

    Random rnd=new Random();
    int confirmCode=rnd.nextInt(10000)+1000;

    @Override
    public OrderDto save(OrderDto orderDto) {
        return mapper.toDto(rep.save(mapper.toEntity(orderDto)));
    }

    @Override
    public OrderDto findById(Long id) {

        return mapper.toDto(rep.findById(id).orElseThrow(() -> new RuntimeException("Запись не найдена")));
    }

    @Override
    public List<OrderDto> findAll() {

        return mapper.toDtos(rep.findAll());
    }

    @Override
    public OrderDto findOrderByConfirmCode(int confirmCode) {
        return mapper.toDto(rep.findOrderByCode(confirmCode));
    }

    @Override
    public Response saveAll(List<OrderDto> list) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setStatus(OrderStatusEnum.CANCELED);
            save(list.get(i));
        }
            return new Response("Успешно");
    }

    @Override
    public List<OrderDto> findOrderByMaster_Id(Long masterId) {
        return mapper.toDtos(rep.findOrderByMaster_Id(masterId));
    }

    @Override
    public OrderDto delete(Long id) {
        return null;
    }

    @Override
    public Response create(OrderRequest order) throws ParseException {
        //Найти клиента, если его нет, ошибка код 404
        // Найти мастера,если нет 404
        //Найти день недели appointmentDate
        //найти график мастера на этот appointmentDate
        // по дню недели найти график
        //проверка на график мастера
        //проверка на ордерс
        //ытащить по мастеру
        //нужно добавить еще время процедуры прежде чем сравнивать ордерс
        ClientDto clientDto = clientService.findById(order.getClientId());
        MasterDto masterDto = masterService.findById(order.getMasterId());
        List<ScheduleDto> scheduleDtos = scheduleService.findScheduleByMasterId(masterDto.getId());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(order.getAppointmentDate());
        WorkDayEnum workDayEnum = WorkDayEnum.getValue(calendar.get(Calendar.DAY_OF_WEEK));

        ScheduleDto scheduleDto = null;

        for (int i = 0; i < scheduleDtos.size(); i++) {
            if (scheduleDtos.get(i).getWorkDay().equals(workDayEnum)) {
                scheduleDto = scheduleDtos.get(i);
                break;}
            }

            if (scheduleDto==null){
                throw new RuntimeException("В этот день мастер не работает!");
            }

        LocalTime startTime = scheduleDto.getStartTime();
        LocalTime endTime = scheduleDto.getEndTime();
        LocalTime orderTime = LocalDateTime.ofInstant(order.getAppointmentDate().toInstant(),
                ZoneId.systemDefault()).toLocalTime();

        if (!(orderTime.isAfter(startTime) & orderTime.isBefore(endTime))){
            throw new RuntimeException("Мастер в это время не работает");
        }


        List<OrderDto> orders = findOrderByMaster_Id(order.getMasterId());
        OrderDto orderDto = new OrderDto();

        for (int i = 0; i < orders.size(); i++) {
            //проверка на статус если делетед пропускаем,если будет конфирм попробуйте через некоторое время
            LocalDateTime appointmentDate = orders.get(i).getAppointmentDate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            LocalDateTime newDate = order.getAppointmentDate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            if (appointmentDate.equals(newDate)) {
                throw new RuntimeException("Это время уже занято");
            }
        }

        orderDto.setAppointmentDate(order.getAppointmentDate());
        orderDto.setMaster(masterDto);
        orderDto.setClient(clientDto);
        orderDto.setConfirmCode(confirmCode);
        save(orderDto);

        try {
            emailSenderService.sendEmail(orderDto.getClient().getEmail(),
                    orderDto.getClient().getName(),
                    orderDto.getAppointmentDate(),orderDto.getConfirmCode());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return new Response("Запись успешно оформлена!");
    }

    @Override
    public Response confirm(int code) {
        // Найти заявку по айди
        //проверка на код неверный ошибка, верный идем дальше
        // добавить проверку на время , если прошел час ошибку , если нет идем дальше
        // переводим заявку в статус confirm

        OrderDto orderDto=findOrderByConfirmCode(code);

        if(orderDto==null || orderDto.getConfirmCode()!=code) {
            throw new NotFoundEx("Неправильный код подтверждения!");
        }else {
            if (checkDate(orderDto.getAddDate())) {
                System.out.println(checkDate(orderDto.getAddDate()));
                orderDto.setStatus(OrderStatusEnum.CANCELED);
                save(orderDto);
                throw new ClientUnauthorizedEx("Время подтверждения истекло. Запишитесь снова.");
            } else {
                orderDto.setStatus(OrderStatusEnum.CONFIRM);
                save(orderDto);
            }
        }
        return new Response("Код подтвержден. Вы успешно записались на процедуру.");
    }

    @Override
    public void checkSuspendOrders() {
        //вытащить все заявки со статусом саспенд,если прошел час переводите на статус делетед
        //saveAll метод что бы все вместе

        List<OrderDto>orders=findAll();
        List<OrderDto>suspendOrders=new ArrayList<>();

        for (int i = 0; i < orders.size(); i++) {
            if(orders.get(i).getStatus().equals(OrderStatusEnum.SUSPEND))
                suspendOrders.add(orders.get(i));

        }

        saveAll(suspendOrders);
        System.out.println("Все неподтверженные заявки успешно удалены!");
    }


    @Override
    public boolean checkDate(Date updateDate) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(updateDate);
            calendar.add(Calendar.HOUR,1);
            Date date = calendar.getTime();
            return new Date().after(date);
        }


}



//TODO https://stackoverflow.com/questions/29927362/localtime-from-date
        //TODO add exc with 404
//TODO написать метод который возвращает прошел час или нет
