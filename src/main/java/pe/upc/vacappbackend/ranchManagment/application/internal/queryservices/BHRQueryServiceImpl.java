package pe.upc.vacappbackend.ranchManagment.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.upc.vacappbackend.ranchManagment.domain.model.aggregates.BovineHealthRecord;
import pe.upc.vacappbackend.ranchManagment.domain.model.queries.GetBHRByIdQuery;
import pe.upc.vacappbackend.ranchManagment.domain.model.queries.GetBHRsByStableIdQuery;
import pe.upc.vacappbackend.ranchManagment.domain.services.BHRQueryService;
import pe.upc.vacappbackend.ranchManagment.infrastructure.persistence.jpa.repositories.BHRRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BHRQueryServiceImpl implements BHRQueryService {
    private final BHRRepository bhrRepository;

    public BHRQueryServiceImpl(BHRRepository bhrRepository) {
        this.bhrRepository = bhrRepository;
    }

    @Override
    public Optional<BovineHealthRecord> handle(GetBHRByIdQuery query){
        return bhrRepository.findById(query.bhrId());
    }

    @Override
    public List<BovineHealthRecord> handle(GetBHRsByStableIdQuery query){
        return bhrRepository.findByStableId(query.stableId());
    }


}
