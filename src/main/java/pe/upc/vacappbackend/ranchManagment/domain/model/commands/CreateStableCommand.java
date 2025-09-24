package pe.upc.vacappbackend.ranchManagment.domain.model.commands;

public record CreateStableCommand(
    String name,
    Integer limit,
    String location,
    Long userId
) {

}
