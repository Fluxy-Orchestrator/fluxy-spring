package org.fluxy.spring.persistence.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;
@Entity
@Table(name = "fluxy_task")
@Getter
@Setter
@NoArgsConstructor
public class FluxyTaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private int version;
    @Column
    private String description;
}
