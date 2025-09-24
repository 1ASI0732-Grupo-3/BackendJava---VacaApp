package pe.upc.vacappbackend.ranchManagment.domain.services;

import pe.upc.vacappbackend.ranchManagment.domain.model.aggregates.BovineHealthRecord;
import pe.upc.vacappbackend.ranchManagment.domain.model.commands.CreateBovineHealthRecordCommand;
import pe.upc.vacappbackend.ranchManagment.domain.model.commands.DeleteBovineHealthRecordCommand;

import java.util.Optional;

public interface BHRCommandService {
    Optional<BovineHealthRecord> handle(CreateBovineHealthRecordCommand command);
    void handle(DeleteBovineHealthRecordCommand command);
}
