package pe.upc.vacappbackend.ranchManagment.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.upc.vacappbackend.ranchManagment.domain.model.aggregates.VaccinationRecord;
import pe.upc.vacappbackend.ranchManagment.domain.model.commands.CreateVaccinationCommand;
import pe.upc.vacappbackend.ranchManagment.domain.model.commands.DeleteVaccinationRecordCommand;
import pe.upc.vacappbackend.ranchManagment.domain.services.VRCommandService;
import pe.upc.vacappbackend.ranchManagment.infrastructure.persistence.jpa.repositories.BHRRepository;
import pe.upc.vacappbackend.ranchManagment.infrastructure.persistence.jpa.repositories.VRRepository;

import java.util.Optional;

@Service
public class VRCommandServiceImpl implements VRCommandService {
    private final VRRepository vrRepository;
    private final BHRRepository bhrRepository;

    public VRCommandServiceImpl(VRRepository vrRepository, BHRRepository bhrRepository) {
        this.bhrRepository = bhrRepository;
        this.vrRepository = vrRepository;
    }

    @Override
    public Optional<VaccinationRecord> handle(CreateVaccinationCommand command) {
        if (!bhrRepository.existsById(command.bovineId())) {
            throw new IllegalArgumentException("Bovine with id %s not found".formatted(command.bovineId()));
        }
        var vr = new VaccinationRecord(command);
        try {
            vrRepository.save(vr);
            return Optional.of(vr);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving the VaccinationRecord: %s".formatted(e.getMessage()));
        }
    }

    @Override
    public void handle(DeleteVaccinationRecordCommand command) {
       if (!vrRepository.existsById(command.VaccinationRecordId())) {
            throw new IllegalArgumentException("VaccinationRecord with id %s not found".formatted(command.VaccinationRecordId()));
        }
        try {
            vrRepository.deleteById(command.VaccinationRecordId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error deleting VaccinationRecord: %s".formatted(e.getMessage()));
        }
    }
}
