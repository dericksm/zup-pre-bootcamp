package com.derick.zupbootcamp.services;

import com.derick.zupbootcamp.domain.entities.ClientCPF;
import com.derick.zupbootcamp.repositories.BaseRepository;
import com.derick.zupbootcamp.repositories.ClientCPFRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientCPFService extends AbstractService<ClientCPF> {

    @Autowired
    private ClientCPFRepository clientCPFRepository;

    @Override
    public BaseRepository<ClientCPF> getRepository() {
        return clientCPFRepository;
    }
}
