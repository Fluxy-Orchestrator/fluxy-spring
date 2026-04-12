package org.fluxy.spring.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "fluxy_condition")
@Getter
@Setter
@NoArgsConstructor
public class ConditionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "connection_id", nullable = false)
    private ConnectionEntity connection;

    @Column(name = "operator_type")
    private String operatorType;

    @Column(name = "operator_name", nullable = false)
    private String operatorName;

    @Column(name = "expected_value")
    private String expectedValue;

    @Column(name = "variable_path", nullable = false)
    private String variablePath;
}

