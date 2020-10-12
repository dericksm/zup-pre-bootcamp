package com.derick.zupbootcamp.services;

import com.derick.zupbootcamp.domain.entities.ClientAccount;
import com.derick.zupbootcamp.domain.entities.Payment;
import com.derick.zupbootcamp.domain.enums.PaymentStatus;
import com.derick.zupbootcamp.repositories.ClientAccountRepository;
import com.derick.zupbootcamp.repositories.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PaymentProcessingService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentProcessingService.class);

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ClientAccountRepository clientAccountRepository;

    @Async("threadPoolTaskExecutor")
    @Scheduled(fixedRate = 5000)
    public CompletableFuture<Void> processPayment() throws InterruptedException {
        logger.info("Payment processing cycle started up");
        List<Payment> payments = paymentRepository.findByPaymentStatusOrderByPaymentDate(PaymentStatus.RECEIVED.getValue());
        ClientAccount account = null;
        if (!payments.isEmpty()) {
            for (Payment payment : payments) {
                logger.info("Processing payment with id: " + payment.getId());
                account = clientAccountRepository.findByAccountNumberAndAgency(payment.getDestinationAccount(), payment.getDestinationAgency());
                if (account != null) {
                    logger.info("Updating account balance");
                    account.setBalance(account.getBalance() + payment.getValue());
                    account = clientAccountRepository.save(account);
                    payment.setPaymentStatus(PaymentStatus.PROCESSED);
                    payment = paymentRepository.save(payment);
                } else {
                    payment.setPaymentStatus(PaymentStatus.INVALID);
                    payment = paymentRepository.save(payment);
                    logger.info("The system haven't found the account with number: " + payment.getDestinationAccount());
                }
            }
        } else {
            logger.info("The system haven't found any unprocessed payment");
        }

        return CompletableFuture.completedFuture(null);
    }

}
