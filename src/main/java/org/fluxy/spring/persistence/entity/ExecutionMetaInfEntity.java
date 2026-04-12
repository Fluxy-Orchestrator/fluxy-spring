package org.fluxy.spring.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "fluxy_execution_meta_inf")
@Getter
@Setter
@NoArgsConstructor
public class ExecutionMetaInfEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "execution_context_id", nullable = false, unique = true)
    private ExecutionContextEntity executionContext;

    @OneToMany(mappedBy = "executionMetaInf", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExecutionStepRecordEntity> stepRecords = new ArrayList<>();
}

