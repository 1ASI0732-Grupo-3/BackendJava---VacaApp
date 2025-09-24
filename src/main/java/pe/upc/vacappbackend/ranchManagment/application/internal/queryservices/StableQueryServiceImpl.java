package pe.upc.vacappbackend.ranchManagment.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.upc.vacappbackend.ranchManagment.domain.model.aggregates.Stable;
import pe.upc.vacappbackend.ranchManagment.domain.model.queries.GetStableByIdQuery;
import pe.upc.vacappbackend.ranchManagment.domain.model.queries.GetStablesByUserIdQuery;
import pe.upc.vacappbackend.ranchManagment.domain.services.StableQueryService;
import pe.upc.vacappbackend.ranchManagment.infrastructure.persistence.jpa.repositories.StableRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StableQueryServiceImpl implements StableQueryService {
    private final StableRepository stableRepository;

    public StableQueryServiceImpl(StableRepository stableRepository) {
        this.stableRepository = stableRepository;
    }

    @Override
    public Optional<Stable> handle(GetStableByIdQuery query){
        return stableRepository.findById(query.stableId());
    }

    @Override
    public List<Stable> handle(GetStablesByUserIdQuery query){
        return stableRepository.findByUserId(query.userId());
    }
}
