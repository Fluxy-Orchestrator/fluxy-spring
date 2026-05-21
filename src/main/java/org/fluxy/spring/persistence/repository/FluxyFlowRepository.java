package org.fluxy.spring.persistence.repository;

import org.fluxy.spring.persistence.entity.FluxyFlowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FluxyFlowRepository extends JpaRepository<FluxyFlowEntity, UUID> {

    Optional<FluxyFlowEntity> findByName(String name);

    List<FluxyFlowEntity> findByType(String type);

    /**
     * Carga un flow con todas sus connections y sus conditions en una sola query,
     * evitando problemas de N+1 al mapear ConnectionDto.
     */
    @Query("SELECT DISTINCT f FROM FluxyFlowEntity f " +
           "LEFT JOIN FETCH f.connections c " +
           "LEFT JOIN FETCH c.conditions " +
           "LEFT JOIN FETCH c.fromStep fs " +
           "LEFT JOIN FETCH fs.step " +
           "LEFT JOIN FETCH c.toStep ts " +
           "LEFT JOIN FETCH ts.step " +
           "WHERE f.id = :id")
    Optional<FluxyFlowEntity> findByIdWithConnections(@Param("id") UUID id);
}

