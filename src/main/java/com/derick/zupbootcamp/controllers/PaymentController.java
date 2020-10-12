package com.derick.zupbootcamp.controllers;

import com.derick.zupbootcamp.domain.dto.BaseConverter;
import com.derick.zupbootcamp.domain.dto.PaymentDTO;
import com.derick.zupbootcamp.domain.dto.PaymentDTOList;
import com.derick.zupbootcamp.domain.entities.Payment;
import com.derick.zupbootcamp.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/payments")
public class PaymentController extends BaseConverter<Payment, PaymentDTO> {

    @Autowired
    private PaymentService paymentService;

    @Transactional
    @PostMapping("/receive-payment-list")
    public ResponseEntity<Void> receivedPayments(@RequestBody @Valid PaymentDTOList receivedPayments) {
        if (!receivedPayments.getList().isEmpty()) {
            Payment payment = null;
            for (PaymentDTO receivedPayment : receivedPayments.getList()) {
                payment = convertToEntity(receivedPayment);
                payment = paymentService.insert(payment);
            }
        }
        return ResponseEntity.ok().build();
    }

    @Transactional
    @PostMapping("/receive-payment")
    public ResponseEntity<Void> receivedPayments(@RequestBody @Valid PaymentDTO receivedPayment) {
        Payment payment = null;
        payment = convertToEntity(receivedPayment);
        payment = paymentService.insert(payment);

        return ResponseEntity.ok().build();
    }

}
