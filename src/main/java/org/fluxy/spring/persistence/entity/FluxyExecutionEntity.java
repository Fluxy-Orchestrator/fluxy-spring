package org.fluxy.spring.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fluxy.core.model.ExecutionStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Entidad JPA que representa una instancia de ejecucion de un flow.
 *
 * <p>Aisla el estado de cada corrida: un mismo flow puede tener multiples
 * ejecuciones simultaneas, cada una con su propio contexto, estado y traza
 * de steps.</p>
 *
 * <p>La clave de idempotencia ({@code idempotencyKey}) es un hash SHA-256
 * derivado de las referencias iniciales del contexto. La constraint
 * {@code UNIQUE(flow_id, idempotency_key)} garantiza que no se creen
 * ejecuciones duplicadas para el mismo flow con las mismas referencias,
 * salvo que la ejecucion anterior ya este {@code FINISHED}.</p>
 */
@Entity
@Table(name = "fluxy_execution",
       uniqueConstraints = @UniqueConstraint(
               name = "uk_execution_flow_idempotency",
               columnNames = {"flow_id", "idempotency_key"}))
@Getter
@Setter
@NoArgsConstructor
public class FluxyExecutionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flow_id", nullable = false)
    private FluxyFlowEntity flow;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "execution_context_id", nullable = false, unique = true)
    private ExecutionContextEntity context;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExecutionStatus status;

    @Column(name = "idempotency_key", nullable = false)
    private String idempotencyKey;

    @OneToMany(mappedBy = "execution", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExecutionStepRecordEntity> stepRecords = new ArrayList<>();
}

