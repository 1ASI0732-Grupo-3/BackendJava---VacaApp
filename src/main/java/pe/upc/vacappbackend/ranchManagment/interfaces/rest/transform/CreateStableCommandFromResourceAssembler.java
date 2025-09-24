package pe.upc.vacappbackend.ranchManagment.interfaces.rest.transform;


import pe.upc.vacappbackend.ranchManagment.domain.model.commands.CreateStableCommand;
import pe.upc.vacappbackend.ranchManagment.interfaces.rest.resources.CreateStableResource;

public class CreateStableCommandFromResourceAssembler {
    public static CreateStableCommand toCommandFromResource(CreateStableResource resource) {
        return new CreateStableCommand(
                resource.name(),
                resource.limit(),
                resource.location(),
                resource.userId()
        );
    }
}
