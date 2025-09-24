package pe.upc.vacappbackend.ranchManagment.domain.services;

import pe.upc.vacappbackend.ranchManagment.domain.model.aggregates.Stable;
import pe.upc.vacappbackend.ranchManagment.domain.model.commands.CreateStableCommand;
import pe.upc.vacappbackend.ranchManagment.domain.model.commands.DeleteStableCommand;

import java.util.Optional;

public interface StableCommandService {
    Optional<Stable> handle(CreateStableCommand command);
    void handle(DeleteStableCommand command);

}
