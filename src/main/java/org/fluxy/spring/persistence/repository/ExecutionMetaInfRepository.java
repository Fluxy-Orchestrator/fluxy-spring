package org.fluxy.spring.persistence.repository;

import org.fluxy.spring.persistence.entity.ExecutionContextEntity;
import org.fluxy.spring.persistence.entity.ExecutionMetaInfEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ExecutionMetaInfRepository extends JpaRepository<ExecutionMetaInfEntity, UUID> {

    Optional<ExecutionMetaInfEntity> findByExecutionContext(ExecutionContextEntity executionContext);
}

