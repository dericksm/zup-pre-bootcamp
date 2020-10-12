package com.derick.zupbootcamp.services;

import com.derick.zupbootcamp.domain.entities.Address;
import com.derick.zupbootcamp.domain.entities.Client;
import com.derick.zupbootcamp.repositories.AddressRepository;
import com.derick.zupbootcamp.repositories.BaseRepository;
import com.derick.zupbootcamp.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService extends AbstractService<Address> {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public BaseRepository<Address> getRepository() {
        return addressRepository;
    }
}
