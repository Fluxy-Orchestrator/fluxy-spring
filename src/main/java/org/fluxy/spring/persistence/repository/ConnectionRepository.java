package org.fluxy.spring.persistence.repository;

import org.fluxy.spring.persistence.entity.ConnectionEntity;
import org.fluxy.spring.persistence.entity.FlowStepEntity;
import org.fluxy.spring.persistence.entity.FluxyFlowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ConnectionRepository extends JpaRepository<ConnectionEntity, UUID> {

    List<ConnectionEntity> findByFlow(FluxyFlowEntity flow);

    List<ConnectionEntity> findByFromStep(FlowStepEntity fromStep);

    List<ConnectionEntity> findByToStep(FlowStepEntity toStep);
}

