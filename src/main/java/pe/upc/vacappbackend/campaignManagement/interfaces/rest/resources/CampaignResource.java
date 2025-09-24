package pe.upc.vacappbackend.campaignManagement.interfaces.rest.resources;

import pe.upc.vacappbackend.campaignManagement.domain.model.valueobjects.C_Status;

import java.time.LocalDate;


public record CampaignResource(
    Long id,
    String name,
    String description,
    LocalDate startDate,
    LocalDate endDate,
    C_Status status,
    Long userId
) {

}
