package kg.megacom.beauty_salon.mappers;

import kg.megacom.beauty_salon.models.entities.Master;
import kg.megacom.beauty_salon.models.dto.MasterDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface MasterMapper extends BaseMapper<Master, MasterDto> {
    MasterMapper INSTANCE= Mappers.getMapper(MasterMapper.class);
}
