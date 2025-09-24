package pe.upc.vacappbackend.ranchManagment.interfaces.rest.resources;

public record Resource_Stable(
    Long id,
    String name,
    Integer limit,
    String location,
    Long userId

) {

}
