package org.fluxy.spring.persistence.repository;

import org.fluxy.spring.persistence.entity.ExecutionMetaInfEntity;
import org.fluxy.spring.persistence.entity.ExecutionStepRecordEntity;
import org.fluxy.spring.persistence.entity.FlowStepEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ExecutionStepRecordRepository extends JpaRepository<ExecutionStepRecordEntity, UUID> {

    List<ExecutionStepRecordEntity> findByExecutionMetaInf(ExecutionMetaInfEntity executionMetaInf);

    List<ExecutionStepRecordEntity> findByFlowStep(FlowStepEntity flowStep);
}

