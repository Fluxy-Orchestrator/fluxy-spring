package org.fluxy.spring.persistence.repository;

import org.fluxy.spring.persistence.entity.ConditionEntity;
import org.fluxy.spring.persistence.entity.ConnectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ConditionRepository extends JpaRepository<ConditionEntity, UUID> {

    List<ConditionEntity> findByConnection(ConnectionEntity connection);
}

