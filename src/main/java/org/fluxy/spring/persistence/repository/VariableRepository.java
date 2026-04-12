package org.fluxy.spring.persistence.repository;

import org.fluxy.spring.persistence.entity.ExecutionContextEntity;
import org.fluxy.spring.persistence.entity.VariableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VariableRepository extends JpaRepository<VariableEntity, UUID> {

    List<VariableEntity> findByExecutionContext(ExecutionContextEntity executionContext);

    Optional<VariableEntity> findByExecutionContextAndName(ExecutionContextEntity executionContext, String name);
}

