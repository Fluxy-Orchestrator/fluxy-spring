package org.fluxy.spring.persistence.repository;

import org.fluxy.spring.persistence.entity.ExecutionContextEntity;
import org.fluxy.spring.persistence.entity.ReferenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReferenceRepository extends JpaRepository<ReferenceEntity, UUID> {

    List<ReferenceEntity> findByExecutionContext(ExecutionContextEntity executionContext);

    Optional<ReferenceEntity> findByExecutionContextAndRefType(ExecutionContextEntity executionContext, String refType);
}

