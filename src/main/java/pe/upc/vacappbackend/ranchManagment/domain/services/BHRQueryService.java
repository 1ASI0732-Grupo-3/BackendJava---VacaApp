package pe.upc.vacappbackend.ranchManagment.domain.services;

import pe.upc.vacappbackend.ranchManagment.domain.model.aggregates.BovineHealthRecord;
import pe.upc.vacappbackend.ranchManagment.domain.model.queries.GetBHRByIdQuery;
import pe.upc.vacappbackend.ranchManagment.domain.model.queries.GetBHRsByStableIdQuery;

import java.util.List;
import java.util.Optional;

public interface BHRQueryService {
    Optional<BovineHealthRecord> handle(GetBHRByIdQuery query);
    List<BovineHealthRecord> handle(GetBHRsByStableIdQuery query);
}
