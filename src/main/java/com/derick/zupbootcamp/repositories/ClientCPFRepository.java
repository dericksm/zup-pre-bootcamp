package com.derick.zupbootcamp.repositories;

import com.derick.zupbootcamp.domain.entities.Client;
import com.derick.zupbootcamp.domain.entities.ClientCPF;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientCPFRepository extends BaseRepository<ClientCPF> {
}
