package kg.megacom.beauty_salon.service;

import kg.megacom.beauty_salon.models.dto.ScheduleDto;
import kg.megacom.beauty_salon.models.enums.WorkDayEnum;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public interface ScheduleService extends BaseService<ScheduleDto>{
    List<ScheduleDto> findScheduleByMasterId(Long id);
     Map<WorkDayEnum,String>scheduleMap(Long masterId);
    ScheduleDto create(LocalTime startTime, LocalTime endTime, WorkDayEnum workDay);
}
