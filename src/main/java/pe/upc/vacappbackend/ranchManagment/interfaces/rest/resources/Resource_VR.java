package pe.upc.vacappbackend.ranchManagment.interfaces.rest.resources;

import java.util.Date;

public record Resource_VR(
    Long id,
    String name,
    String vaccineType,
    Date vaccineDate,
    String vaccineImg,
    Long bovineId
) {

}
