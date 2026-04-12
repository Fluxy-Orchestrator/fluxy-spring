package org.fluxy.spring.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "fluxy_execution_context")
@Getter
@Setter
@NoArgsConstructor
public class ExecutionContextEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String version;

    @OneToMany(mappedBy = "executionContext", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VariableEntity> variables = new ArrayList<>();

    @OneToMany(mappedBy = "executionContext", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReferenceEntity> references = new ArrayList<>();

    @OneToOne(mappedBy = "executionContext", cascade = CascadeType.ALL, orphanRemoval = true)
    private ExecutionMetaInfEntity executionMetaInf;
}

