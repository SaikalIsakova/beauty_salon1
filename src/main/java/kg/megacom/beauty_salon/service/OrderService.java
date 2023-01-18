package kg.megacom.beauty_salon.service;

import kg.megacom.beauty_salon.models.dto.OrderDto;
import kg.megacom.beauty_salon.models.request.OrderRequest;
import kg.megacom.beauty_salon.response.Response;

import java.text.ParseException;

public interface OrderService extends BaseService<OrderDto>{

    Response create(OrderRequest order)throws ParseException;

}
