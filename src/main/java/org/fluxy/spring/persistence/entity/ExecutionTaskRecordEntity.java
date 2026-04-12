package org.fluxy.spring.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fluxy.core.model.TaskResult;
import org.fluxy.core.model.TaskStatus;

import java.util.UUID;

@Entity
@Table(name = "fluxy_execution_task_record")
@Getter
@Setter
@NoArgsConstructor
public class ExecutionTaskRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "execution_step_record_id", nullable = false)
    private ExecutionStepRecordEntity executionStepRecord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "step_task_id", nullable = false)
    private StepTaskEntity stepTask;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    private TaskResult result;
}

