package kg.megacom.beauty_salon.service.impl;

import kg.megacom.beauty_salon.dao.MasterScheduleRep;
import kg.megacom.beauty_salon.mappers.MasterScheduleMapper;
import kg.megacom.beauty_salon.models.dto.MasterDto;
import kg.megacom.beauty_salon.models.dto.MasterScheduleDto;
import kg.megacom.beauty_salon.models.dto.ScheduleDto;
import kg.megacom.beauty_salon.models.enums.WorkDayEnum;
import kg.megacom.beauty_salon.response.Response;
import kg.megacom.beauty_salon.service.MasterScheduleService;
import kg.megacom.beauty_salon.service.MasterService;
import kg.megacom.beauty_salon.service.ScheduleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MasterScheduleServiceImpl implements MasterScheduleService {

MasterScheduleMapper mapper=MasterScheduleMapper.INSTANCE;

    private final MasterScheduleRep rep;
    private final MasterService masterService;
    private final ScheduleService scheduleService;

    public MasterScheduleServiceImpl(MasterScheduleRep rep, MasterService masterService, ScheduleService service) {
        this.rep = rep;
        this.masterService = masterService;
        this.scheduleService = service;
    }



    @Override
    public MasterScheduleDto save(MasterScheduleDto masterScheduleDto) {

        return mapper.toDto(rep.save(mapper.toEntity(masterScheduleDto)));
    }

    @Override
    public MasterScheduleDto findById(Long id) {

        return mapper.toDto(rep.findById(id).orElseThrow(()->new RuntimeException("График мастера не найден")));
    }

    @Override
    public List<MasterScheduleDto> findAll() {

        return mapper.toDtos(rep.findAll());
    }

    @Override
    public MasterScheduleDto delete(Long id) {
        return null;
    }

    @Override
    public Response create(Long masterId, List<Long> scheduleIds) {
        MasterDto masterDto = masterService.findById(masterId);
        for (Long id:scheduleIds){
            ScheduleDto scheduleDto=scheduleService.findById(id);
            save(new MasterScheduleDto(masterDto,scheduleDto));
        }

        return new Response("Success");
    }

    @Override
    public Map<WorkDayEnum, String> getSchedule(Long masterId) {
        masterService.findById(masterId);



        return null;
    }
}
