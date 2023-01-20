package kg.megacom.beauty_salon.service.impl;

import kg.megacom.beauty_salon.dao.MasterRep;
import kg.megacom.beauty_salon.exceptions.NotFoundEx;
import kg.megacom.beauty_salon.mappers.MasterMapper;
import kg.megacom.beauty_salon.models.dto.MasterDto;
import kg.megacom.beauty_salon.models.dto.SalonDto;
import kg.megacom.beauty_salon.models.request.SaveMasterRequest;
import kg.megacom.beauty_salon.service.MasterService;
import kg.megacom.beauty_salon.service.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MasterServiceImpl implements MasterService {

    @Autowired
    MasterRep rep;
    MasterMapper mapper=MasterMapper.INSTANCE;

    @Autowired
    SalonService salonService;

    @Override
    public MasterDto save(MasterDto masterDto) {
        return mapper.toDto(rep.save(mapper.toEntity(masterDto)));
    }

    @Override
    public MasterDto findById(Long id) {

        return mapper.toDto(rep.findById(id).orElseThrow(()->new NotFoundEx("Мастер не найден")));
    }

    @Override
    public List<MasterDto> findAll() {

        return mapper.toDtos(rep.findAll());
    }

    @Override
    public MasterDto delete(Long id) {
        return null;
    }

    @Override
    public List<MasterDto> findScheduleByMasterId(Long id) {
        return mapper.toDtos(rep.findScheduleByMasterId(id));
    }

    @Override
    public List<MasterDto> findBySalonId(Long id) {
        return mapper.toDtos(rep.findMasterBySalonId(id));
    }

    @Override
    public MasterDto create(SaveMasterRequest master) {

        SalonDto salon = salonService.findById(master.getSaloonId());

        MasterDto masterDto = new MasterDto();
        masterDto.setName(master.getName());
        masterDto.setSurname(master.getSurname());
        masterDto.setWorkType(master.getWorkType());
        masterDto.setSalon(salon);
        return save(masterDto);
    }
}
