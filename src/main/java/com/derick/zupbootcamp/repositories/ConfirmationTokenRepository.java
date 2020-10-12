package com.derick.zupbootcamp.repositories;

import com.derick.zupbootcamp.domain.entities.ConfirmationToken;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRepository extends BaseRepository<ConfirmationToken> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
