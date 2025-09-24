package pe.upc.vacappbackend.ranchManagment.interfaces.rest.transform;

import pe.upc.vacappbackend.ranchManagment.domain.model.commands.CreateBovineHealthRecordCommand;
import pe.upc.vacappbackend.ranchManagment.interfaces.rest.resources.CreateBHRResource;

public class CreateBHRCommandFromResourceAssembler {
    public static CreateBovineHealthRecordCommand toCommandFromResource(CreateBHRResource resource) {
        return new CreateBovineHealthRecordCommand(
                resource.name(),
                resource.gender(),
                resource.birthDate(),
                resource.breed(),
                resource.bovineImg(),
                resource.stableId()
        );
    }
}
