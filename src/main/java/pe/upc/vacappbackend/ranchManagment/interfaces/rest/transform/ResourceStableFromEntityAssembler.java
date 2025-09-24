package pe.upc.vacappbackend.ranchManagment.interfaces.rest.transform;

import pe.upc.vacappbackend.ranchManagment.domain.model.aggregates.Stable;
import pe.upc.vacappbackend.ranchManagment.interfaces.rest.resources.Resource_Stable;

public class ResourceStableFromEntityAssembler {
    public static Resource_Stable toResourceFromEntity(Stable entity) {
        return new Resource_Stable(
            entity.getId(),
            entity.getName(),
            entity.getMaxLimit(),
            entity.getLocation(),
            entity.getUserId()
        );
    }
}
