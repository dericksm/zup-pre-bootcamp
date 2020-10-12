package com.derick.zupbootcamp.controllers;

import com.derick.zupbootcamp.domain.dto.AddressDTO;
import com.derick.zupbootcamp.domain.entities.Address;
import com.derick.zupbootcamp.domain.entities.Client;
import com.derick.zupbootcamp.services.AbstractService;
import com.derick.zupbootcamp.services.AddressService;
import com.derick.zupbootcamp.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/address")
public class AdressController extends AbstractController<Address, AddressDTO> {

    @Autowired
    private AddressService addressService;

    @Autowired
    private ClientService clientService;

    @Override
    public AbstractService<Address> getService() {
        return addressService;
    }

    @Override
    public ResponseEntity<Address> insert(@Valid @RequestBody AddressDTO objDTO) {
        Client client = clientService.findById(objDTO.getClientId());
        Address address = convertToEntity(objDTO);
        address.setClient(client);
        address = addressService.insert(address);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(address.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
