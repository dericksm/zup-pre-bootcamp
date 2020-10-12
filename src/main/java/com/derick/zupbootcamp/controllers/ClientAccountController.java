package com.derick.zupbootcamp.controllers;

import com.derick.zupbootcamp.domain.dto.ClientAccountDTO;
import com.derick.zupbootcamp.domain.dto.PasswordDTO;
import com.derick.zupbootcamp.domain.entities.ClientAccount;
import com.derick.zupbootcamp.domain.entities.ConfirmationToken;
import com.derick.zupbootcamp.repositories.ConfirmationTokenRepository;
import com.derick.zupbootcamp.services.AbstractService;
import com.derick.zupbootcamp.services.AccountSecurityService;
import com.derick.zupbootcamp.services.ClientAccountService;
import com.derick.zupbootcamp.services.exceptions.TokenValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/accounts")
public class ClientAccountController extends AbstractController<ClientAccount, ClientAccountDTO> {

    @Autowired
    private ClientAccountService clientAccountService;

    @Autowired
    private AccountSecurityService accountSecurityService;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public AbstractService<ClientAccount> getService() {
        return clientAccountService;
    }

    @PostMapping("/change-password")
    public ResponseEntity<Void> savePassword(@Valid @RequestBody PasswordDTO passwordDTO) {

        String result = accountSecurityService.validatePasswordResetToken(passwordDTO.getToken());

        if (result != null) {
            throw new TokenValidationException(result);
        }

        ConfirmationToken confirmationToken = confirmationTokenRepository.findByConfirmationToken(passwordDTO.getToken());
        if (confirmationToken != null && !confirmationToken.isUsed()) {
            ClientAccount clientAccount = confirmationToken.getUser();
            clientAccount.setPassword(bCryptPasswordEncoder.encode(passwordDTO.getPassword()));
            clientAccountService.update(clientAccount);
            confirmationToken.setUsed(true);
            confirmationTokenRepository.save(confirmationToken);
            return ResponseEntity.ok().build();
        } else if (confirmationToken == null) {
            throw new TokenValidationException("Invalid token");
        } else if (confirmationToken.isUsed()) {
            throw new TokenValidationException("Token was already used");
        }
        return null;
    }
}
