package com.derick.zupbootcamp.repositories;

import com.derick.zupbootcamp.domain.entities.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Integer> {
}