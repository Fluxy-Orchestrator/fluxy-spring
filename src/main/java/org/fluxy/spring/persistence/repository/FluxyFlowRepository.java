package org.fluxy.spring.persistence.repository;

import org.fluxy.spring.persistence.entity.FluxyFlowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FluxyFlowRepository extends JpaRepository<FluxyFlowEntity, UUID> {

    Optional<FluxyFlowEntity> findByName(String name);

    List<FluxyFlowEntity> findByType(String type);
}

