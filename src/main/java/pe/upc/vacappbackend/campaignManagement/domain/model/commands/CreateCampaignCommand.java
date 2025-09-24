package pe.upc.vacappbackend.campaignManagement.domain.model.commands;

import pe.upc.vacappbackend.campaignManagement.domain.model.valueobjects.C_Status;

import java.time.LocalDate;


public record CreateCampaignCommand(
    String name,
    String description,
    LocalDate startDate,
    LocalDate endDate,
    C_Status status,
    Long userId
) {

}
