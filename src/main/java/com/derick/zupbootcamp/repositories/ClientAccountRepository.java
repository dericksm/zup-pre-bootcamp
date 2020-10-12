package com.derick.zupbootcamp.repositories;

import com.derick.zupbootcamp.domain.entities.AccountProposal;
import com.derick.zupbootcamp.domain.entities.ClientAccount;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientAccountRepository extends BaseRepository<ClientAccount> {
    ClientAccount findByAccountNumberAndAgency(Integer accountNumber, Integer agency);

    Optional<ClientAccount> findByProposal(AccountProposal accountProposal);
}
