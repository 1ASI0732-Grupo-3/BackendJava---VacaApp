package pe.upc.vacappbackend.ranchManagment.domain.services;

import pe.upc.vacappbackend.ranchManagment.domain.model.aggregates.Stable;
import pe.upc.vacappbackend.ranchManagment.domain.model.queries.GetStableByIdQuery;
import pe.upc.vacappbackend.ranchManagment.domain.model.queries.GetStablesByUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface StableQueryService {
    Optional<Stable> handle(GetStableByIdQuery query);
    List<Stable> handle(GetStablesByUserIdQuery query);
}
