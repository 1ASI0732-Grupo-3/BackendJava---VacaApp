package pe.upc.vacappbackend.ranchManagment.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.upc.vacappbackend.ranchManagment.domain.model.aggregates.BovineHealthRecord;
import pe.upc.vacappbackend.ranchManagment.domain.model.commands.CreateBovineHealthRecordCommand;
import pe.upc.vacappbackend.ranchManagment.domain.model.commands.DeleteBovineHealthRecordCommand;
import pe.upc.vacappbackend.ranchManagment.domain.services.BHRCommandService;
import pe.upc.vacappbackend.ranchManagment.infrastructure.persistence.jpa.repositories.BHRRepository;
import pe.upc.vacappbackend.ranchManagment.infrastructure.persistence.jpa.repositories.StableRepository;

import java.util.Optional;

@Service
public class BHRCommandServiceImpl implements BHRCommandService {
    private final BHRRepository bhrRepository;
    private final StableRepository stableRepository;

    public BHRCommandServiceImpl(BHRRepository bhrRepository, StableRepository stableRepository) {
        this.stableRepository = stableRepository;
        this.bhrRepository = bhrRepository;
    }

    @Override
    public Optional<BovineHealthRecord> handle(CreateBovineHealthRecordCommand command) {
        var stable = stableRepository.findById(command.stableId())
                .orElseThrow(() -> new IllegalArgumentException("Stable with id %s not found".formatted(command.stableId())));

        var bovineHealthRecord = new BovineHealthRecord(command,stable);
        try {
            bhrRepository.save(bovineHealthRecord);
            return Optional.of(bovineHealthRecord);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving the BovineHealthRecord: %s".formatted(e.getMessage()));
        }
    }

    @Override
    public void handle(DeleteBovineHealthRecordCommand command) {
       if (!bhrRepository.existsById(command.BovineHealthRecordId())) {
            throw new IllegalArgumentException("BovineHealthRecord with id %s not found".formatted(command.BovineHealthRecordId()));
        }
        try {
            bhrRepository.deleteById(command.BovineHealthRecordId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error deleting BovineHealthRecord: %s".formatted(e.getMessage()));
        }
    }
}
