package kg.megacom.beauty_salon.service;

import kg.megacom.beauty_salon.models.dto.SalonDto;
import kg.megacom.beauty_salon.models.request.SalonRequest;

public interface SalonService extends BaseService<SalonDto>{
    SalonDto create(SalonRequest salon);
}
