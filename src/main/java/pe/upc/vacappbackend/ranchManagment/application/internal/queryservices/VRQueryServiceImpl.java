package pe.upc.vacappbackend.ranchManagment.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.upc.vacappbackend.ranchManagment.domain.model.aggregates.VaccinationRecord;
import pe.upc.vacappbackend.ranchManagment.domain.model.queries.GetVaccinationRecordByBHRIdQuery;
import pe.upc.vacappbackend.ranchManagment.domain.services.VRQueryService;
import pe.upc.vacappbackend.ranchManagment.infrastructure.persistence.jpa.repositories.VRRepository;

import java.util.Optional;

@Service
public class VRQueryServiceImpl implements VRQueryService {
    private final VRRepository vrRepository;

    public VRQueryServiceImpl(VRRepository vrRepository) {
        this.vrRepository = vrRepository;
    }

    @Override
    public Optional<VaccinationRecord> handle(GetVaccinationRecordByBHRIdQuery query){
        return vrRepository.findByBovineId(query.bhrId());
    }


}
