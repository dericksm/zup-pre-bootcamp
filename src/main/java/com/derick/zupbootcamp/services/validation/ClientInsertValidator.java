package com.derick.zupbootcamp.services.validation;

import com.derick.zupbootcamp.controllers.exceptions.FieldMessage;
import com.derick.zupbootcamp.domain.dto.ClientDTO;
import com.derick.zupbootcamp.domain.entities.Client;
import com.derick.zupbootcamp.repositories.ClientRepository;
import com.derick.zupbootcamp.services.utils.BrUtils;
import com.derick.zupbootcamp.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientDTO> {

    @Autowired
    private ClientRepository repository;

    @Override
    public void initialize(ClientInsert ann) {
    }

    @Override
    public boolean isValid(ClientDTO obj, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (!BrUtils.isValidCPF(obj.getCpf())) {
            list.add(new FieldMessage("Cpf", "Invalid CPF"));
        }

        if (!DateUtils.hasAgeRequirement(obj.getBirthDate())) {
            list.add(new FieldMessage("BirthDate", "Client must be over 18 years old"));
        }

        Client client = repository.findByEmail(obj.getEmail());
        if (client != null) {
            list.add(new FieldMessage("Email", "E-mail already in use"));
        }

        client = repository.findByCpf(obj.getCpf()).orElse(null);
        if (client != null) {
            list.add(new FieldMessage("Cpf", "CPF already in use"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }

        return list.isEmpty();
    }
}