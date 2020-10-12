package com.derick.zupbootcamp.services;

import com.derick.zupbootcamp.domain.entities.AccountProposal;
import com.derick.zupbootcamp.domain.entities.ClientAccount;
import com.derick.zupbootcamp.repositories.BaseRepository;
import com.derick.zupbootcamp.repositories.ClientAccountRepository;
import com.derick.zupbootcamp.services.exceptions.ObjectNotFoundException;
import com.derick.zupbootcamp.utils.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ClientAccountService extends AbstractService<ClientAccount> {

    @Autowired
    private ClientAccountRepository clientAccountRepository;

    @Override
    public BaseRepository<ClientAccount> getRepository() {
        return clientAccountRepository;
    }

    public ClientAccount generateNewAccount(AccountProposal proposal){
        ClientAccount account = new ClientAccount();
        account.setProposal(proposal);
        account.setClient(proposal.getClient());

        account.setAgency(MathUtils.generateRandom(4));
        account.setBankNumber(MathUtils.generateRandom(3));
        account.setAccountNumber(MathUtils.generateRandom(8));

        account = insert(account);
        return account;
    }

    public ClientAccount findByAccountProposal(AccountProposal accountProposal){
        return clientAccountRepository.findByProposal(accountProposal).orElseThrow(() -> new ObjectNotFoundException("Entity not found."));
    }
}
