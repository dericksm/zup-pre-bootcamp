package com.derick.zupbootcamp.services;

import com.derick.zupbootcamp.domain.entities.AccountProposal;
import com.derick.zupbootcamp.domain.entities.ClientAccount;
import com.derick.zupbootcamp.domain.entities.ConfirmationToken;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendEmail(SimpleMailMessage msg);

    void sendHtmlEmail(MimeMessage msg);

    void sendAccountConfirmationEmail(ClientAccount clientAccount);

    void sendAccountConfirmationHtmlEmail(ClientAccount clientAccount);

    void sendRequestToAcceptAccountProposalEmail(AccountProposal accountProposal);

    void sendRequestToAcceptAccountProposalHtmlEmail(AccountProposal accountProposal);

    void sendTokenEmail(ConfirmationToken confirmationToken);

    void sendTokenHtmlEmail(ConfirmationToken confirmationToken);
}
