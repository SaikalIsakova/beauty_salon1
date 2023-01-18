package kg.megacom.beauty_salon.service.impl;

import kg.megacom.beauty_salon.dao.SalonRep;
import kg.megacom.beauty_salon.mappers.SalonMapper;
import kg.megacom.beauty_salon.models.dto.SalonDto;
import kg.megacom.beauty_salon.models.request.SalonRequest;
import kg.megacom.beauty_salon.service.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalonServiceImpl implements SalonService {

    @Autowired
    SalonRep rep;

    SalonMapper mapper=SalonMapper.INSTANCE;

    @Override
    public SalonDto save(SalonDto salonDto) {
        return mapper.toDto(rep.save(mapper.toEntity(salonDto)));
    }

    @Override
    public SalonDto findById(Long id) {

        return mapper.toDto(rep.findById(id).orElseThrow(()->new RuntimeException("Салон не найден")));
    }

    @Override
    public List<SalonDto> findAll() {

        return mapper.toDtos(rep.findAll());
    }

    @Override
    public SalonDto delete(Long id) {
        return null;
    }

    @Override
    public SalonDto create(SalonRequest salon) {
        SalonDto salonDto=new SalonDto();
        salonDto.setName(salon.getName());
        salonDto.setAddress(salon.getAddress());
        salonDto.setPhoneNumber(salon.getNumber());
        return save(salonDto);
    }
}
