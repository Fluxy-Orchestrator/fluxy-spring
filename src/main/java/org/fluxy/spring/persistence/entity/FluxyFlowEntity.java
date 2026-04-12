package org.fluxy.spring.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "fluxy_flow")
@Getter
@Setter
@NoArgsConstructor
public class FluxyFlowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String type;

    private String description;

    @OneToMany(mappedBy = "flow", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("stepOrder ASC")
    private List<FlowStepEntity> steps = new ArrayList<>();

    @OneToMany(mappedBy = "flow", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConnectionEntity> connections = new ArrayList<>();
}

