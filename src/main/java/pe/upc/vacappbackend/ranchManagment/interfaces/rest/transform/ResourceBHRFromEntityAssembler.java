package pe.upc.vacappbackend.ranchManagment.interfaces.rest.transform;

import pe.upc.vacappbackend.ranchManagment.domain.model.aggregates.BovineHealthRecord;
import pe.upc.vacappbackend.ranchManagment.interfaces.rest.resources.Resource_BHR;

public class ResourceBHRFromEntityAssembler {
    public static Resource_BHR toResourceFromEntity(BovineHealthRecord entity) {
        return new Resource_BHR(
            entity.getId(),
            entity.getName(),
            entity.getGender(),
            entity.getBirthDate(),
            entity.getBreed(),
            entity.getBovineImg(),
            entity.getStable().getId()
        );
    }
}
