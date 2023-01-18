package kg.megacom.beauty_salon.mappers;

import kg.megacom.beauty_salon.models.entities.Salon;
import kg.megacom.beauty_salon.models.dto.SalonDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface SalonMapper extends BaseMapper<Salon, SalonDto> {
    SalonMapper INSTANCE= Mappers.getMapper(SalonMapper.class);
}
