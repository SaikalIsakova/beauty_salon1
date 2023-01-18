package kg.megacom.beauty_salon.mappers;

import kg.megacom.beauty_salon.models.entities.Client;
import kg.megacom.beauty_salon.models.dto.ClientDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper extends BaseMapper<Client, ClientDto>{
    ClientMapper INSTANCE= Mappers.getMapper(ClientMapper.class);
}
