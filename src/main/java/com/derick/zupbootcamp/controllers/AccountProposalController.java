package com.derick.zupbootcamp.controllers;

import com.derick.zupbootcamp.domain.dto.AccountProposalDTO;
import com.derick.zupbootcamp.domain.entities.AccountProposal;
import com.derick.zupbootcamp.domain.entities.Client;
import com.derick.zupbootcamp.domain.enums.ProposalStatus;
import com.derick.zupbootcamp.services.AbstractService;
import com.derick.zupbootcamp.services.AccountProposalService;
import com.derick.zupbootcamp.services.ClientService;
import com.derick.zupbootcamp.services.exceptions.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/account-proposal")
public class AccountProposalController extends AbstractController<AccountProposal, AccountProposalDTO> {

    @Autowired
    private AccountProposalService accountProposalService;

    @Autowired
    private ClientService clientService;

    @Override
    public AbstractService<AccountProposal> getService() {
        return accountProposalService;
    }

    @Override
    public ResponseEntity<AccountProposal> insert(@Valid @RequestBody AccountProposalDTO objDTO) {
        AccountProposal accountProposal = new AccountProposal();

        Client client = clientService.findById(objDTO.getClientId());

        if(accountProposalService.findByClientId(client.getId()) != null) {
            throw new DataIntegrityException("Account Proposal for client with id: " + client.getId() + " has already been created");
        }

        accountProposal.setClient(client);
        accountProposal.setStatus(ProposalStatus.toEnum(objDTO.getStatus()));
        accountProposal = accountProposalService.insert(accountProposal);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(accountProposal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody AccountProposalDTO objDTO) {
        AccountProposal proposal = accountProposalService.findById(id);
        ProposalStatus oldStatus = proposal.getStatus();
        Client client = clientService.findById(objDTO.getClientId());
        proposal.setClient(client);
        proposal.setStatus(ProposalStatus.toEnum(objDTO.getStatus()));

        proposal = accountProposalService.update(proposal, oldStatus);

        return ResponseEntity.noContent().build();
    }



}
