package pe.upc.vacappbackend.ranchManagment.interfaces.rest.transform;

import pe.upc.vacappbackend.ranchManagment.domain.model.commands.CreateVaccinationCommand;
import pe.upc.vacappbackend.ranchManagment.interfaces.rest.resources.CreateVRResource;

public class CreateVRCommandFromResourceAssembler {
    public static CreateVaccinationCommand toCommandFromResource(CreateVRResource resource) {
        return new CreateVaccinationCommand(
                resource.name(),
                resource.vaccineType(),
                resource.vaccineDate(),
                resource.vaccineImg(),
                resource.bovineId()
        );
    }
}
