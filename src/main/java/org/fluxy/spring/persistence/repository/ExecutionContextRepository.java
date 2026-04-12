package org.fluxy.spring.persistence.repository;

import org.fluxy.spring.persistence.entity.ExecutionContextEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ExecutionContextRepository extends JpaRepository<ExecutionContextEntity, UUID> {

    List<ExecutionContextEntity> findByType(String type);

    List<ExecutionContextEntity> findByTypeAndVersion(String type, String version);
}

