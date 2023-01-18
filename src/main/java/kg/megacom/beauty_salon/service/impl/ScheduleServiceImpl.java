package kg.megacom.beauty_salon.service.impl;

import kg.megacom.beauty_salon.dao.ScheduleRep;
import kg.megacom.beauty_salon.mappers.ScheduleMapper;
import kg.megacom.beauty_salon.models.dto.ScheduleDto;
import kg.megacom.beauty_salon.models.entities.Schedule;
import kg.megacom.beauty_salon.models.enums.WorkDayEnum;
import kg.megacom.beauty_salon.service.MasterService;
import kg.megacom.beauty_salon.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRep rep;
    ScheduleMapper mapper = ScheduleMapper.INSTANCE;
    @Autowired
    MasterService masterService;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    @Override
    public ScheduleDto save(ScheduleDto scheduleDto) {
//        Schedule schedule = new Schedule();
//        schedule.setEndTime(LocalTime.now());
//        schedule.setStartTime(LocalTime.now());
//        schedule.setWorkDay(WorkDayEnum.SATURDAY);
//        rep.save(schedule);
//        return null;
        return mapper.toDto(rep.save(mapper.toEntity(scheduleDto)));
    }

    @Override
    public ScheduleDto findById(Long id) {

        return mapper.toDto(rep.findById(id).orElseThrow(() -> new RuntimeException("График не найден")));
    }

    @Override
    public List<ScheduleDto> findAll() {
//
//        List<Schedule> list = rep.findAll();
//        return list.stream()
//                .map(item -> {
//                    ScheduleDto scheduleDto = new ScheduleDto(item.getId(),
//                            item.getStartTime(),
//                            item.getEndTime(),
//                            item.getWorkDay());
//                    return scheduleDto;
//                })
//                .collect(Collectors.toList());
        return mapper.toDtos(rep.findAll());
    }

    @Override
    public ScheduleDto delete(Long id) {

        return null;
    }


    @Override
    public List<ScheduleDto> findScheduleByMasterId(Long id) {
        return mapper.toDtos(rep.findScheduleByMasterId(id));
    }

    @Override
    public Map<WorkDayEnum, String> scheduleMap(Long masterId) {
        //MasterDto masterDto=masterService.findById(masterId);

        List<ScheduleDto> list = mapper.toDtos(rep.findScheduleByMasterId(masterId));

        Map<WorkDayEnum, String> map = new HashMap<>();

        for (ScheduleDto item : list) {
            map.put(item.getWorkDay(), item.getStartTime() + " " + item.getEndTime());
        }
        return map;
    }

    @Override
    public ScheduleDto create(LocalTime startTime, LocalTime endTime, WorkDayEnum workDay) {
        return save(new ScheduleDto(startTime,endTime,workDay));
    }

}

