package pe.upc.vacappbackend.ranchManagment.interfaces.rest.resources;

import java.util.Date;

public record CreateBHRResource(
    String name,
    String gender,
    Date birthDate,
    String breed,
    String bovineImg,
    Long stableId

) {

}
