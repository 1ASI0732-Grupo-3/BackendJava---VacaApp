package pe.upc.vacappbackend.ranchManagment.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.upc.vacappbackend.ranchManagment.domain.model.aggregates.BovineHealthRecord;
import pe.upc.vacappbackend.ranchManagment.domain.model.aggregates.Stable;

import java.util.List;
import java.util.Optional;

public interface BHRRepository extends JpaRepository<BovineHealthRecord, Long> {
    Optional<BovineHealthRecord>findById(Long id);
    List<BovineHealthRecord> findByStableId(Long stableId);
}
