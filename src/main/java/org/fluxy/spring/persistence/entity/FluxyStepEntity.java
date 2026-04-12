package org.fluxy.spring.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "fluxy_step")
@Getter
@Setter
@NoArgsConstructor
public class FluxyStepEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "step", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("taskOrder ASC")
    private List<StepTaskEntity> tasks = new ArrayList<>();
}

