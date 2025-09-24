package pe.upc.vacappbackend.ranchManagment.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.upc.vacappbackend.ranchManagment.domain.model.aggregates.Stable;

import java.util.List;
import java.util.Optional;

public interface StableRepository extends JpaRepository<Stable, Long> {
    Optional<Stable>findById(Long id);
    List<Stable> findByUserId(Long userId);
}
