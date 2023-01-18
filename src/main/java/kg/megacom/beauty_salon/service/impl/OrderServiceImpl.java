package kg.megacom.beauty_salon.service.impl;

import kg.megacom.beauty_salon.dao.OrderRep;
import kg.megacom.beauty_salon.exceptions.NotFoundEx;
import kg.megacom.beauty_salon.mappers.OrderMapper;
import kg.megacom.beauty_salon.models.dto.ClientDto;
import kg.megacom.beauty_salon.models.dto.MasterDto;
import kg.megacom.beauty_salon.models.dto.OrderDto;
import kg.megacom.beauty_salon.models.dto.ScheduleDto;
import kg.megacom.beauty_salon.models.enums.WorkDayEnum;
import kg.megacom.beauty_salon.models.request.OrderRequest;
import kg.megacom.beauty_salon.response.Response;
import kg.megacom.beauty_salon.service.*;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
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
    public OrderDto delete(Long id) {
        return null;
    }

    @Override
    public Response create(OrderRequest order) throws ParseException {
        //Найти клиента, если его нет, ошибка код 404
        ClientDto clientDto = clientService.findById(order.getClientId());

        // Найти мастера,если нет 404
        MasterDto masterDto = masterService.findById(order.getMasterId());


        //Найти день недели appointmentDate
        List<ScheduleDto> scheduleDtos = scheduleService.findScheduleByMasterId(masterDto.getId());
        ScheduleDto scheduleDto = new ScheduleDto();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(order.getAppointmentDate());
        WorkDayEnum workDayEnum = WorkDayEnum.getValue(calendar.get(Calendar.DAY_OF_WEEK));

        //найти график мастера на этот appointmentDate
        for (int i = 0; i < scheduleDtos.size(); i++) {
            if (scheduleDtos.get(i).getWorkDay().equals(workDayEnum)) {
                // по дню недели найти график
                scheduleDto = scheduleDtos.get(i);
                break;
            } else {
                new RuntimeException("Мастер в этот день не работает");
            }
        }

        //проверка на график мастера
        LocalTime startTime = scheduleDto.getStartTime();
        LocalTime endTime = scheduleDto.getEndTime();

        //нужно добавить еще время процедуры прежде чем сравнивать ордерс
        LocalTime orderTime = LocalDateTime.ofInstant(order.getAppointmentDate().toInstant(),
                ZoneId.systemDefault()).toLocalTime();

        if (startTime.isAfter(orderTime) && endTime.isBefore(orderTime)) {
            throw new RuntimeException("Мастер в это время не работает");
        }

        //проверка на ордерс
        List<OrderDto> orders = findAll();
        OrderDto orderDto = new OrderDto();

        for (int i = 0; i < orders.size(); i++) {
            LocalDateTime appointmentDate = orders.get(i).getAppointmentDate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            System.out.println(appointmentDate);
            LocalDateTime newDate = order.getAppointmentDate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            System.out.println(newDate);

            if (appointmentDate.equals(newDate)) {
                throw new RuntimeException("Это время уже занято");
            }

        }

        orderDto.setAppointmentDate(order.getAppointmentDate());
        orderDto.setMaster(masterDto);
        orderDto.setClient(clientDto);
        save(orderDto);
        return new Response("Success");

    }

}

//TODO https://stackoverflow.com/questions/29927362/localtime-from-date
        //TODO add exc with 404 code
