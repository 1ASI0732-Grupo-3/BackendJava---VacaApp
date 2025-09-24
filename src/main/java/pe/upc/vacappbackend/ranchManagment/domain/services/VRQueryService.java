package pe.upc.vacappbackend.ranchManagment.domain.services;

import pe.upc.vacappbackend.ranchManagment.domain.model.aggregates.VaccinationRecord;
import pe.upc.vacappbackend.ranchManagment.domain.model.queries.GetVaccinationRecordByBHRIdQuery;

import java.util.Optional;

public interface VRQueryService {
    Optional<VaccinationRecord> handle(GetVaccinationRecordByBHRIdQuery query);
}
