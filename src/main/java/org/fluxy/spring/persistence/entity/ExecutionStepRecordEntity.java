package org.fluxy.spring.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "fluxy_execution_step_record")
@Getter
@Setter
@NoArgsConstructor
public class ExecutionStepRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "execution_meta_inf_id", nullable = false)
    private ExecutionMetaInfEntity executionMetaInf;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flow_step_id", nullable = false)
    private FlowStepEntity flowStep;

    @OneToMany(mappedBy = "executionStepRecord", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExecutionTaskRecordEntity> taskRecords = new ArrayList<>();
}

