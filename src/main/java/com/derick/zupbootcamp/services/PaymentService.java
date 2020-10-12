package com.derick.zupbootcamp.services;

import com.derick.zupbootcamp.domain.entities.Payment;
import com.derick.zupbootcamp.repositories.BaseRepository;
import com.derick.zupbootcamp.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService extends AbstractService<Payment> {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public BaseRepository<Payment> getRepository() {
        return paymentRepository;
    }
}
