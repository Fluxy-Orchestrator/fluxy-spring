package org.fluxy.spring.persistence.repository;

import org.fluxy.spring.persistence.entity.FluxyStepEntity;
import org.fluxy.spring.persistence.entity.StepTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StepTaskRepository extends JpaRepository<StepTaskEntity, UUID> {

    List<StepTaskEntity> findByStep(FluxyStepEntity step);

    List<StepTaskEntity> findByStepOrderByTaskOrderAsc(FluxyStepEntity step);
}

