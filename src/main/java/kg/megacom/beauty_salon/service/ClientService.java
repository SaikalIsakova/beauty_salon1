package kg.megacom.beauty_salon.service;

import kg.megacom.beauty_salon.models.dto.ClientDto;
import kg.megacom.beauty_salon.models.entities.Client;
import kg.megacom.beauty_salon.models.request.ClientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface ClientService extends BaseService<ClientDto>{
    ClientDto create(ClientRequest clientRequest);
}
