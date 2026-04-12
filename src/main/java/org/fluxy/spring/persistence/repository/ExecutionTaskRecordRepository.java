package org.fluxy.spring.persistence.repository;

import org.fluxy.spring.persistence.entity.ExecutionStepRecordEntity;
import org.fluxy.spring.persistence.entity.ExecutionTaskRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ExecutionTaskRecordRepository extends JpaRepository<ExecutionTaskRecordEntity, UUID> {

    List<ExecutionTaskRecordEntity> findByExecutionStepRecord(ExecutionStepRecordEntity executionStepRecord);
}

