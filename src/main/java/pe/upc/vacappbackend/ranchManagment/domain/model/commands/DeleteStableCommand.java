package pe.upc.vacappbackend.ranchManagment.domain.model.commands;

public record DeleteStableCommand(
    Long stableId
) {
    public DeleteStableCommand {
        if (stableId == null || stableId <= 0) {
            throw new IllegalArgumentException("User ID must be a positive number.");
        }
    }
}
