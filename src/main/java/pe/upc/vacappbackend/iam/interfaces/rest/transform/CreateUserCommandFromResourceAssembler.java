package pe.upc.vacappbackend.iam.interfaces.rest.transform;

import pe.upc.vacappbackend.iam.domain.model.commands.CreateUserCommand;
import pe.upc.vacappbackend.iam.interfaces.rest.resources.CreateUserResource;

public class CreateUserCommandFromResourceAssembler {
    public static CreateUserCommand toCommandFromResource(CreateUserResource resource) {
        return new CreateUserCommand(
                resource.username(),
                resource.password(),
                resource.email(),
                resource.role()
        );
    }
}
