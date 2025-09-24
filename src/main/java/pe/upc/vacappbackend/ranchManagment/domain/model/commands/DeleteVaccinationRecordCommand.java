package pe.upc.vacappbackend.ranchManagment.domain.model.commands;

public record DeleteVaccinationRecordCommand(
    Long VaccinationRecordId
) {
    public DeleteVaccinationRecordCommand {
        if (VaccinationRecordId == null || VaccinationRecordId <= 0) {
            throw new IllegalArgumentException("VaccinationRecord ID must be a positive number.");
        }
    }
}
