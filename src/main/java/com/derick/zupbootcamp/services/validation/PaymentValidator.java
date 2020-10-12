package com.derick.zupbootcamp.services.validation;

import com.derick.zupbootcamp.controllers.exceptions.FieldMessage;
import com.derick.zupbootcamp.domain.dto.ClientDTO;
import com.derick.zupbootcamp.domain.dto.PaymentDTO;
import com.derick.zupbootcamp.domain.entities.Client;
import com.derick.zupbootcamp.domain.entities.Payment;
import com.derick.zupbootcamp.repositories.ClientRepository;
import com.derick.zupbootcamp.repositories.PaymentRepository;
import com.derick.zupbootcamp.services.utils.BrUtils;
import com.derick.zupbootcamp.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentValidator implements ConstraintValidator<PaymentValidation, PaymentDTO> {

    @Autowired
    private PaymentRepository repository;

    @Override
    public void initialize(PaymentValidation ann) {
    }

    @Override
    public boolean isValid(PaymentDTO paymentDTO, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        Payment payment = repository.findById(paymentDTO.getId()).orElse(null);
        if (payment != null) {
            list.add(new FieldMessage("ProcessedPayment", "Payment already was processed"));
        }

        if (paymentDTO.getOriginCPF() != null && !BrUtils.isValidCPF(paymentDTO.getOriginCPF())) {
            list.add(new FieldMessage("originCPF", "Invalid CPF format"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }

        return list.isEmpty();
    }
}