package kg.megacom.beauty_salon.service.impl;

import kg.megacom.beauty_salon.dao.ClientRep;
import kg.megacom.beauty_salon.exceptions.NotFoundEx;
import kg.megacom.beauty_salon.mappers.ClientMapper;
import kg.megacom.beauty_salon.models.dto.ClientDto;
import kg.megacom.beauty_salon.models.request.ClientRequest;
import kg.megacom.beauty_salon.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    ClientMapper mapper=ClientMapper.INSTANCE;
    @Autowired
    ClientRep rep;


    @Override
    public ClientDto save(ClientDto clientDto) {
        return mapper.toDto(rep.save(mapper.toEntity(clientDto)));
    }

    @Override
    public ClientDto findById(Long id) {

        return mapper.toDto(rep.findById(id).orElseThrow(()->new NotFoundEx("Клиент не найден")));
    }

    @Override
    public List<ClientDto> findAll() {

        return mapper.toDtos(rep.findAll());
    }

    @Override
    public ClientDto delete(Long id) {

        return null;
    }

    @Override
    public ClientDto create(ClientRequest clientRequest) {
        ClientDto clientDto=new ClientDto();
        clientDto.setName(clientRequest.getName());
        clientDto.setSurname(clientRequest.getSurname());
        clientDto.setPhoneNumber(clientRequest.getNumber());
        clientDto.setEmail(clientRequest.getEmail());

        return save(clientDto);
    }
}
