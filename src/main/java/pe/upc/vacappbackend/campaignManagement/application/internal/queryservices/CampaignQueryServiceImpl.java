package pe.upc.vacappbackend.campaignManagement.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.upc.vacappbackend.campaignManagement.domain.model.aggregates.Campaign;
import pe.upc.vacappbackend.campaignManagement.domain.model.queries.GetCampaignByIdQuery;
import pe.upc.vacappbackend.campaignManagement.domain.model.queries.GetCampaignsByUserIdQuery;
import pe.upc.vacappbackend.campaignManagement.domain.services.CampaignQueryService;
import pe.upc.vacappbackend.campaignManagement.infrastructure.persistence.jpa.repositories.CampaignRepository;
import pe.upc.vacappbackend.iam.domain.model.aggregates.User;
import pe.upc.vacappbackend.iam.domain.model.queries.GetUserByIdQuery;
import pe.upc.vacappbackend.iam.domain.services.UserQueryService;
import pe.upc.vacappbackend.iam.infrastructure.persistence.jpa.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CampaignQueryServiceImpl implements CampaignQueryService {
    private final CampaignRepository campaignRepository;

    public CampaignQueryServiceImpl(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public Optional<Campaign> handle(GetCampaignByIdQuery query){
        return campaignRepository.findById(query.campaignId());
    }

    @Override
    public List<Campaign> handle(GetCampaignsByUserIdQuery query) {
        return campaignRepository.findCampaignsByUserId(query.userId());
    }

}
