package pe.upc.vacappbackend.campaignManagement.domain.services;

import pe.upc.vacappbackend.campaignManagement.domain.model.aggregates.Campaign;
import pe.upc.vacappbackend.campaignManagement.domain.model.queries.GetCampaignByIdQuery;
import pe.upc.vacappbackend.campaignManagement.domain.model.queries.GetCampaignsByUserIdQuery;


import java.util.List;
import java.util.Optional;

public interface CampaignQueryService {
    Optional<Campaign> handle(GetCampaignByIdQuery query);
    List<Campaign> handle(GetCampaignsByUserIdQuery query);
}
