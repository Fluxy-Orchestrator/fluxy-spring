package org.fluxy.spring.persistence.repository;

import org.fluxy.core.model.ExecutionStatus;
import org.fluxy.spring.persistence.entity.FluxyExecutionEntity;
import org.fluxy.spring.persistence.entity.FluxyFlowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FluxyExecutionRepository extends JpaRepository<FluxyExecutionEntity, UUID>, JpaSpecificationExecutor<FluxyExecutionEntity> {

    /**
     * Idempotencia: busca una ejecucion activa (no FINISHED) para el mismo
     * flow con la misma clave de referencias iniciales.
     */
    Optional<FluxyExecutionEntity> findByFlowAndIdempotencyKeyAndStatusNot(
            FluxyFlowEntity flow, String idempotencyKey, ExecutionStatus status);

    /**
     * Busca ejecuciones cuyo contexto contenga una referencia con el tipo
     * y valor indicados. Util para localizar la ejecucion a reanudar
     * desde un listener asincrono.
     */
    @Query("SELECT e FROM FluxyExecutionEntity e " +
           "JOIN e.context c JOIN c.references r " +
           "WHERE r.refType = :refType AND r.value = :refValue")
    List<FluxyExecutionEntity> findByContextReference(
            @Param("refType") String refType,
            @Param("refValue") String refValue);

    List<FluxyExecutionEntity> findByFlow(FluxyFlowEntity flow);
}

