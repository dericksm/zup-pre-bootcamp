package com.derick.zupbootcamp.services;

import com.derick.zupbootcamp.domain.entities.ConfirmationToken;
import com.derick.zupbootcamp.repositories.ConfirmationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;

@Service
public class AccountSecurityService {

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    //seconds
    @Value("${confirmation-token.timeout}")
    private Long tokenTimeout;

    public String validatePasswordResetToken(String token) {
        final ConfirmationToken passToken = confirmationTokenRepository.findByConfirmationToken(token);

        return !isTokenFound(passToken) ? "Invalid Token" : isTokenExpired(passToken) ? "Token Expired" : null;
    }

    private boolean isTokenFound(ConfirmationToken passToken) {
        return passToken != null;
    }

    private boolean isTokenExpired(ConfirmationToken passToken) {
        return passToken.getCreatedDate().plusSeconds(tokenTimeout).isBefore(ZonedDateTime.now());
    }
}
