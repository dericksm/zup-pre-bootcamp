package com.derick.zupbootcamp.services;

import com.derick.zupbootcamp.domain.entities.AccountProposal;
import com.derick.zupbootcamp.domain.entities.ClientAccount;
import com.derick.zupbootcamp.domain.entities.ConfirmationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractMailService implements EmailService {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendAccountConfirmationEmail(ClientAccount clientAccount) {
        SimpleMailMessage sm = prepareSimpleMailMessageFromAccount(clientAccount);
        sendEmail(sm);
    }

    @Override
    public void sendRequestToAcceptAccountProposalEmail(AccountProposal accountProposal) {
        SimpleMailMessage sm = prepareSimpleMailMessageFromAccountProposal(accountProposal);
        sendEmail(sm);
    }

    @Override
    public void sendTokenEmail(ConfirmationToken token) {
        SimpleMailMessage sm = prepareSimpleMailMessageFromConfirmationToken(token);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromAccount(ClientAccount clientAccount) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(clientAccount.getClient().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Pedido confirmado! Código: " + clientAccount.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(clientAccount.toString());
        return sm;
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromAccountProposal(AccountProposal accountProposal) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(accountProposal.getClient().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Proposta de conta não aceita!: " + accountProposal.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(accountProposal.toString());
        return sm;
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromConfirmationToken(ConfirmationToken token) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(token.getUser().getClient().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Troca de senha de sua conta");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(token.toString());
        return sm;
    }

    protected MimeMessage prepareMimeMessageFromAccount(ClientAccount clientAccount) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(clientAccount.getClient().getEmail());
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setSubject(clientAccount.getClient().getFirstName() + ", dados da sua nova conta!");
        mimeMessageHelper.setSentDate(new Date(System.currentTimeMillis()));
        mimeMessageHelper.setText(htmlFromTemplateAccount(clientAccount), true);
        return mimeMessage;
    }

    protected MimeMessage prepareMimeMessageToAccountConfirmation(AccountProposal accountProposal) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(accountProposal.getClient().getEmail());
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setSubject(accountProposal.getClient().getFirstName() + ", estamos aguardando sua aprovação!");
        mimeMessageHelper.setSentDate(new Date(System.currentTimeMillis()));
        mimeMessageHelper.setText(htmlFromTemplateAccountProposal(accountProposal), true);
        return mimeMessage;
    }

    protected MimeMessage prepareMimeMessageConfirmationToken(ConfirmationToken confirmationToken) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(confirmationToken.getUser().getClient().getEmail());
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setSubject("Troca de senha de sua conta");
        mimeMessageHelper.setSentDate(new Date(System.currentTimeMillis()));
        mimeMessageHelper.setText(htmlFromTemplateConfirmationToken(confirmationToken), true);
        return mimeMessage;
    }

    protected String htmlFromTemplateAccount(ClientAccount clientAccount) {
        Context context = new Context();
        context.setVariable("account", clientAccount);
        return templateEngine.process("email/clientAccountConfirmation", context);
    }

    protected String htmlFromTemplateAccountProposal(AccountProposal accountProposal) {
        Context context = new Context();
        context.setVariable("proposal", accountProposal);
        return templateEngine.process("email/accountProposalRequestConfirmation", context);
    }

    protected String htmlFromTemplateConfirmationToken(ConfirmationToken confirmationToken) {
        Context context = new Context();
        context.setVariable("token", confirmationToken);
        return templateEngine.process("email/confirmationToken", context);
    }

    @Override
    public void sendAccountConfirmationHtmlEmail(ClientAccount clientAccount) {
        try {
            MimeMessage sm = prepareMimeMessageFromAccount(clientAccount);
            sendHtmlEmail(sm);
        } catch (MessagingException ex) {
            sendAccountConfirmationEmail(clientAccount);
        }
    }

    @Override
    public void sendRequestToAcceptAccountProposalHtmlEmail(AccountProposal accountProposal) {
        try {
            MimeMessage sm = prepareMimeMessageToAccountConfirmation(accountProposal);
            sendHtmlEmail(sm);
        } catch (MessagingException ex) {
            sendRequestToAcceptAccountProposalEmail(accountProposal);
        }
    }

    @Override
    public void sendTokenHtmlEmail(ConfirmationToken confirmationToken) {
        try {
            MimeMessage sm = prepareMimeMessageConfirmationToken(confirmationToken);
            sendHtmlEmail(sm);
        } catch (MessagingException ex) {
            sendTokenHtmlEmail(confirmationToken);
        }
    }

}
