package kg.megacom.beauty_salon.service;

import kg.megacom.beauty_salon.models.dto.MasterDto;
import kg.megacom.beauty_salon.models.request.SaveMasterRequest;

import java.util.List;

public interface MasterService extends BaseService<MasterDto>{
    List<MasterDto> findScheduleByMasterId(Long id);
    List<MasterDto> findBySalonId(Long id);
    MasterDto create(SaveMasterRequest master);
}
