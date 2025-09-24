package pe.upc.vacappbackend.campaignManagement.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.upc.vacappbackend.campaignManagement.domain.model.aggregates.Campaign;
import pe.upc.vacappbackend.campaignManagement.domain.model.commands.CreateCampaignCommand;
import pe.upc.vacappbackend.campaignManagement.domain.model.commands.DeleteCampaignCommand;
import pe.upc.vacappbackend.campaignManagement.domain.services.CampaignCommandService;
import pe.upc.vacappbackend.campaignManagement.infrastructure.persistence.jpa.repositories.CampaignRepository;


import java.util.Optional;

@Service
public class CampaignCommandServiceImpl implements CampaignCommandService {
    private final CampaignRepository campaignRepository;

    public CampaignCommandServiceImpl(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public Optional<Campaign> handle(CreateCampaignCommand command) {
        var campaign = new Campaign(command);
        try {
            campaignRepository.save(campaign);
            return Optional.of(campaign);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving the Campaign: %s".formatted(e.getMessage()));
        }
    }

    @Override
    public void handle(DeleteCampaignCommand command) {
       if (!campaignRepository.existsById(command.campaignId())) {
            throw new IllegalArgumentException("Campaign with id %s not found".formatted(command.campaignId()));
        }
        try {
            campaignRepository.deleteById(command.campaignId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error deleting Campaign: %s".formatted(e.getMessage()));
        }
    }
}
