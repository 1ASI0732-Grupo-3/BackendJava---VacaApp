package pe.upc.vacappbackend.ranchManagment.interfaces.rest.resources;


public record CreateStableResource(
    String name,
    Integer limit,
    String location,
    Long userId
) {

}
