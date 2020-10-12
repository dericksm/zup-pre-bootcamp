package com.derick.zupbootcamp.controllers;

import com.derick.zupbootcamp.domain.dto.ClientDTO;
import com.derick.zupbootcamp.domain.entities.Client;
import com.derick.zupbootcamp.domain.entities.ClientCPF;
import com.derick.zupbootcamp.services.AbstractService;
import com.derick.zupbootcamp.services.ClientCPFService;
import com.derick.zupbootcamp.services.ClientService;
import com.derick.zupbootcamp.services.DriveService;
import com.derick.zupbootcamp.services.exceptions.DataIntegrityException;
import com.derick.zupbootcamp.services.exceptions.InvalidDataException;
import com.derick.zupbootcamp.services.exceptions.ObjectNotFoundException;
import com.derick.zupbootcamp.services.impl.DriveServiceImpl;
import com.google.api.services.drive.Drive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.net.URI;

@RestController
@RequestMapping(value = "/clients")
public class ClientController extends AbstractController<Client, ClientDTO> {

    @Autowired
    private ClientService clientService;

    @Autowired
    private DriveService driveService;

    @Override
    public AbstractService<Client> getService() {
        return clientService;
    }

    @PostMapping
    public ResponseEntity<Client> insert(@Valid @RequestBody ClientDTO objDTO) {
        Client obj = convertToEntity(objDTO);
        obj.setClientCPF(null);
        obj = getService().insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}/cpf")
    public void showFile(@PathVariable("id") Integer id, HttpServletResponse response, HttpServletRequest request) throws IOException {
        try {
            Client client = clientService.findById(id);
            ByteArrayOutputStream outputStream = driveService.getFile(client.getClientCPF().getDriveFileId());
            response.getOutputStream().write(outputStream.toByteArray());
        } catch (Exception e) {
            response.setStatus(404);
        }
        finally {
            response.getOutputStream().close();
        }
    }
}
