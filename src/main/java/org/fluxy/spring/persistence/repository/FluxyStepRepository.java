package org.fluxy.spring.persistence.repository;

import org.fluxy.spring.persistence.entity.FluxyStepEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FluxyStepRepository extends JpaRepository<FluxyStepEntity, UUID> {

    Optional<FluxyStepEntity> findByName(String name);
}

