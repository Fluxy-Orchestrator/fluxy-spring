package org.fluxy.spring.persistence.repository;
import org.fluxy.spring.persistence.entity.FluxyTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
public interface FluxyTaskRepository extends JpaRepository<FluxyTaskEntity, UUID> {
    Optional<FluxyTaskEntity> findByName(String name);
    Optional<FluxyTaskEntity> findByNameAndVersion(String name, int version);
    List<FluxyTaskEntity> findAllByName(String name);
}
