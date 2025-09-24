package pe.upc.vacappbackend.ranchManagment.interfaces.rest.transform;

import pe.upc.vacappbackend.iam.domain.model.aggregates.User;
import pe.upc.vacappbackend.iam.interfaces.rest.resources.UserResource;
import pe.upc.vacappbackend.ranchManagment.domain.model.aggregates.VaccinationRecord;
import pe.upc.vacappbackend.ranchManagment.interfaces.rest.resources.Resource_VR;

public class ResourceVRFromEntityAssembler {
    public static Resource_VR toResourceFromEntity(VaccinationRecord entity) {
        return new Resource_VR(
            entity.getId(),
            entity.getName(),
            entity.getVaccineType(),
            entity.getVaccineDate(),
            entity.getVaccineImg(),
            entity.getBovineId()
        );
    }
}
