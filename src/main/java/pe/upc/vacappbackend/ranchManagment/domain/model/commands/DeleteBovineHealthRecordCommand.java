package pe.upc.vacappbackend.ranchManagment.domain.model.commands;

public record DeleteBovineHealthRecordCommand(
    Long BovineHealthRecordId
) {
    public DeleteBovineHealthRecordCommand {
        if (BovineHealthRecordId == null || BovineHealthRecordId <= 0) {
            throw new IllegalArgumentException("BovineHealthRecord  ID must be a positive number.");
        }
    }
}
