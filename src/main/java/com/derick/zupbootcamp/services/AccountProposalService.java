package com.derick.zupbootcamp.services;

import com.derick.zupbootcamp.domain.entities.*;
import com.derick.zupbootcamp.domain.enums.ProposalStatus;
import com.derick.zupbootcamp.repositories.AccountProposalRepository;
import com.derick.zupbootcamp.repositories.AddressRepository;
import com.derick.zupbootcamp.repositories.BaseRepository;
import com.derick.zupbootcamp.repositories.ConfirmationTokenRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;

@Service
public class AccountProposalService extends AbstractService<AccountProposal> {

    @Autowired
    private ClientAccountService clientAccountService;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AccountProposalRepository accountProposalRepository;

    @Override
    public BaseRepository<AccountProposal> getRepository() {
        return accountProposalRepository;
    }

    @Override
    public AccountProposal insert(AccountProposal accountProposal) {
        accountProposal = accountProposalRepository.save(accountProposal);

        ClientAccount clientAccount = clientAccountService.generateNewAccount(accountProposal);
        clientAccount = clientAccountService.insert(clientAccount);

        if(ProposalStatus.ACCEPTED.equals(accountProposal.getStatus())) {
            emailService.sendAccountConfirmationHtmlEmail(clientAccount);
        } else if(ProposalStatus.WAITING.equals(accountProposal.getStatus())) {
            emailService.sendRequestToAcceptAccountProposalHtmlEmail(accountProposal);
        }

        return accountProposal;
    }

    public AccountProposal update(AccountProposal accountProposal, ProposalStatus oldStatus) {

        if(ProposalStatus.ACCEPTED.equals(accountProposal.getStatus()) && !accountProposal.getStatus().equals(oldStatus)) {
            ClientAccount clientAccount = clientAccountService.generateNewAccount(accountProposal);
            emailService.sendAccountConfirmationHtmlEmail(clientAccount);
        } else if(ProposalStatus.WAITING.equals(accountProposal.getStatus()) && !accountProposal.getStatus().equals(oldStatus)) {
            emailService.sendRequestToAcceptAccountProposalHtmlEmail(accountProposal);
        } else if(ProposalStatus.APPROVED.equals(accountProposal.getStatus()) && !accountProposal.getStatus().equals(oldStatus)) {
            ClientAccount clientAccount = clientAccountService.findByAccountProposal(accountProposal);
            ConfirmationToken token = new ConfirmationToken(clientAccount);
            token = confirmationTokenRepository.save(token);
            emailService.sendTokenHtmlEmail(token);
        }

        return accountProposal;
    }

    public AccountProposal findByClientId(Integer id){
        return accountProposalRepository.findByClientId(id);
    }
}
