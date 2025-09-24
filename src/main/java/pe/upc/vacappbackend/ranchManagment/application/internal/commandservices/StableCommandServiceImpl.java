package pe.upc.vacappbackend.ranchManagment.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.upc.vacappbackend.ranchManagment.domain.model.aggregates.Stable;
import pe.upc.vacappbackend.ranchManagment.domain.model.commands.CreateStableCommand;
import pe.upc.vacappbackend.ranchManagment.domain.model.commands.DeleteStableCommand;
import pe.upc.vacappbackend.ranchManagment.domain.services.StableCommandService;
import pe.upc.vacappbackend.ranchManagment.infrastructure.persistence.jpa.repositories.StableRepository;

import java.util.Optional;

@Service
public class StableCommandServiceImpl implements StableCommandService {
    private final StableRepository stableRepository;

    public StableCommandServiceImpl(StableRepository stableRepository) {
        this.stableRepository = stableRepository;
    }

    @Override
    public Optional<Stable> handle(CreateStableCommand command) {
        var stable = new Stable(command);
        try {
            stableRepository.save(stable);
            return Optional.of(stable);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving the stable: %s".formatted(e.getMessage()));
        }
    }

    @Override
    public void handle(DeleteStableCommand command) {
       if (!stableRepository.existsById(command.stableId())) {
            throw new IllegalArgumentException("Stable with id %s not found".formatted(command.stableId()));
        }
        try {
            stableRepository.deleteById(command.stableId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error deleting stable: %s".formatted(e.getMessage()));
        }
    }
}
