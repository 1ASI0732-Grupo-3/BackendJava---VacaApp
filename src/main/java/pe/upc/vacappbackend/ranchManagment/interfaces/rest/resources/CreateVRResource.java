package pe.upc.vacappbackend.ranchManagment.interfaces.rest.resources;

import java.util.Date;

public record CreateVRResource(
    String name,
    String vaccineType,
    Date vaccineDate,
    String vaccineImg,
    Long bovineId
) {

}
