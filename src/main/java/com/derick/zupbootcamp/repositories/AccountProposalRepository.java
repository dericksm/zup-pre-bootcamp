package com.derick.zupbootcamp.repositories;

import com.derick.zupbootcamp.domain.entities.AccountProposal;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountProposalRepository extends BaseRepository<AccountProposal> {
    AccountProposal findByClientId(Integer id);
}
