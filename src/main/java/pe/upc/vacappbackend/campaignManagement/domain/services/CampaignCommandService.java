package pe.upc.vacappbackend.campaignManagement.domain.services;

import pe.upc.vacappbackend.campaignManagement.domain.model.aggregates.Campaign;
import pe.upc.vacappbackend.campaignManagement.domain.model.commands.CreateCampaignCommand;
import pe.upc.vacappbackend.campaignManagement.domain.model.commands.DeleteCampaignCommand;
import pe.upc.vacappbackend.iam.domain.model.aggregates.User;
import pe.upc.vacappbackend.iam.domain.model.commands.CreateUserCommand;
import pe.upc.vacappbackend.iam.domain.model.commands.DeleteUserCommand;

import java.util.Optional;

public interface CampaignCommandService {
    Optional<Campaign> handle(CreateCampaignCommand command);
    void handle(DeleteCampaignCommand command);
}
