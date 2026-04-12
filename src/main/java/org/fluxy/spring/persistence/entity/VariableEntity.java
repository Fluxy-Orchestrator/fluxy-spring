package org.fluxy.spring.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "fluxy_variable")
@Getter
@Setter
@NoArgsConstructor
public class VariableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "execution_context_id", nullable = false)
    private ExecutionContextEntity executionContext;
}

