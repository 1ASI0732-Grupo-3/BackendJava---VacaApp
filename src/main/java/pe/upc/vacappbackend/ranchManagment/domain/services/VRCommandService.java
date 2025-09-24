package pe.upc.vacappbackend.ranchManagment.domain.services;

import pe.upc.vacappbackend.ranchManagment.domain.model.aggregates.VaccinationRecord;
import pe.upc.vacappbackend.ranchManagment.domain.model.commands.CreateVaccinationCommand;
import pe.upc.vacappbackend.ranchManagment.domain.model.commands.DeleteVaccinationRecordCommand;

import java.util.Optional;

public interface VRCommandService {
    Optional<VaccinationRecord> handle(CreateVaccinationCommand command);
    void handle(DeleteVaccinationRecordCommand command);
}
