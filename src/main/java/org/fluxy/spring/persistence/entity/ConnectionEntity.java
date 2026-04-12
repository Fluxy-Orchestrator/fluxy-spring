package org.fluxy.spring.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "fluxy_connection")
@Getter
@Setter
@NoArgsConstructor
public class ConnectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flow_id", nullable = false)
    private FluxyFlowEntity flow;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_step_id", nullable = false)
    private FlowStepEntity fromStep;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_step_id", nullable = false)
    private FlowStepEntity toStep;

    @OneToMany(mappedBy = "connection", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConditionEntity> conditions = new ArrayList<>();
}

