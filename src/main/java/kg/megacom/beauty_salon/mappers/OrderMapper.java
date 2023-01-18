package kg.megacom.beauty_salon.mappers;

import kg.megacom.beauty_salon.models.entities.Order;
import kg.megacom.beauty_salon.models.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper extends BaseMapper<Order, OrderDto> {
    OrderMapper INSTANCE= Mappers.getMapper(OrderMapper.class);
}
