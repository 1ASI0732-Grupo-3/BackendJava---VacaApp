package pe.upc.vacappbackend.ranchManagment.domain.model.commands;

import pe.upc.vacappbackend.iam.domain.model.valueobjects.Role;

import java.util.Date;

public record CreateVaccinationCommand(
    String name,
    String vaccineType,
    Date vaccineDate,
    String vaccineImg,
    Long bovineId

) {
    public CreateVaccinationCommand {

    }
}
