package com.derick.zupbootcamp.repositories;

import com.derick.zupbootcamp.domain.entities.Payment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends BaseRepository<Payment> {
    List<Payment> findByPaymentStatusOrderByPaymentDate(Integer status);
}
