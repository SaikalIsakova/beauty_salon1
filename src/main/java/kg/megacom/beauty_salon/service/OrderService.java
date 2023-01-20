package kg.megacom.beauty_salon.service;

import kg.megacom.beauty_salon.models.dto.OrderDto;
import kg.megacom.beauty_salon.models.entities.Order;
import kg.megacom.beauty_salon.models.request.OrderRequest;
import kg.megacom.beauty_salon.response.Response;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface OrderService extends BaseService<OrderDto>{

    Response create(OrderRequest order)throws ParseException;

    Response confirm(int code);

    void checkSuspendOrders();

    List<OrderDto> findOrderByMaster_Id(Long masterId);

    boolean checkDate(Date updateDate);

    OrderDto findOrderByConfirmCode(int confirmCode);

    Response saveAll(List<OrderDto>list);

}
