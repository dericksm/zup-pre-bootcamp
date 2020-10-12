package com.derick.zupbootcamp.services;

import com.derick.zupbootcamp.domain.entities.Client;
import com.derick.zupbootcamp.repositories.BaseRepository;
import com.derick.zupbootcamp.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends AbstractService<Client> {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public BaseRepository<Client> getRepository() {
        return clientRepository;
    }
}
