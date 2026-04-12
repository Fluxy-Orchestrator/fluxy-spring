package org.fluxy.spring.persistence.repository;

import org.fluxy.spring.persistence.entity.ConnectionEntity;
import org.fluxy.spring.persistence.entity.FlowStepEntity;
import org.fluxy.spring.persistence.entity.FluxyFlowEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Repositorio JPA para {@link ConnectionEntity}.
 *
 * <p>Los métodos de consulta utilizan {@link EntityGraph} para cargar en una
 * única query todas las asociaciones necesarias, evitando
 * {@code LazyInitializationException} fuera de sesión transaccional.</p>
 */
public interface ConnectionRepository extends JpaRepository<ConnectionEntity, UUID> {

    /**
     * Devuelve todas las conexiones del flow cuyo step de origen es {@code fromStep},
     * cargando de forma eagerly las condiciones de evaluación, el step destino
     * ({@code toStep}) y la entidad de step subyacente ({@code toStep.step}).
     *
     * <p>Usado durante la ejecución del flow para determinar si alguna conexión
     * aplica tras completar todas las tareas de un step.</p>
     */
    @EntityGraph(attributePaths = {"conditions", "toStep", "toStep.step"})
    List<ConnectionEntity> findByFlowAndFromStep(FluxyFlowEntity flow, FlowStepEntity fromStep);

    /**
     * Devuelve todas las conexiones de un flow, cargando de forma eagerly el
     * step de origen ({@code fromStep}), su entidad subyacente ({@code fromStep.step})
     * y las condiciones asociadas.
     *
     * <p>Usado durante la creación bulk para detectar condiciones potencialmente
     * ambiguas entre conexiones del mismo step de origen.</p>
     */
    @EntityGraph(attributePaths = {"fromStep", "fromStep.step", "conditions"})
    List<ConnectionEntity> findByFlow(FluxyFlowEntity flow);
}
