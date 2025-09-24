package pe.upc.vacappbackend.ranchManagment.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.upc.vacappbackend.ranchManagment.domain.model.aggregates.VaccinationRecord;

import java.util.Optional;

public interface VRRepository extends JpaRepository<VaccinationRecord, Long> {
    Optional<VaccinationRecord>findByBovineId(Long id);
}
