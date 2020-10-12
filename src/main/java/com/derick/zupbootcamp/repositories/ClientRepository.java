package com.derick.zupbootcamp.repositories;

import com.derick.zupbootcamp.domain.entities.Client;
import com.derick.zupbootcamp.domain.entities.ClientAccount;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends BaseRepository<Client> {
    Client findByEmail(String email);

    Optional<Client> findByCpf(String cpf);
}
