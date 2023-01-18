package kg.megacom.beauty_salon.service;

import kg.megacom.beauty_salon.models.dto.MasterScheduleDto;
import kg.megacom.beauty_salon.models.enums.WorkDayEnum;
import kg.megacom.beauty_salon.response.Response;

import java.util.List;
import java.util.Map;

public interface MasterScheduleService extends BaseService<MasterScheduleDto>{
    Response create(Long masterId, List<Long>scheduleIds);
    Map<WorkDayEnum, String> getSchedule(Long masterId);
}

