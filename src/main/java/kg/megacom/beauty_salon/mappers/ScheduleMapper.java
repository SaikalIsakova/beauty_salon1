package kg.megacom.beauty_salon.mappers;

import kg.megacom.beauty_salon.models.dto.ScheduleDto;
import kg.megacom.beauty_salon.models.entities.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ScheduleMapper extends BaseMapper<Schedule,ScheduleDto>{

    ScheduleMapper INSTANCE = Mappers.getMapper(ScheduleMapper.class);


}
