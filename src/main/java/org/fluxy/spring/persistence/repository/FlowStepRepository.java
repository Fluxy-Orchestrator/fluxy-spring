package org.fluxy.spring.persistence.repository;

import org.fluxy.spring.persistence.entity.FlowStepEntity;
import org.fluxy.spring.persistence.entity.FluxyFlowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FlowStepRepository extends JpaRepository<FlowStepEntity, UUID> {

    List<FlowStepEntity> findByFlow(FluxyFlowEntity flow);

    List<FlowStepEntity> findByFlowOrderByStepOrderAsc(FluxyFlowEntity flow);

    Optional<FlowStepEntity> findByFlowAndStepOrder(FluxyFlowEntity flow, Integer stepOrder);
}

