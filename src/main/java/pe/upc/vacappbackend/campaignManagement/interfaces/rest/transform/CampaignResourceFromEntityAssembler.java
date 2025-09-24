package pe.upc.vacappbackend.campaignManagement.interfaces.rest.transform;

import pe.upc.vacappbackend.campaignManagement.domain.model.aggregates.Campaign;
import pe.upc.vacappbackend.campaignManagement.interfaces.rest.resources.CampaignResource;
import pe.upc.vacappbackend.iam.domain.model.aggregates.User;
import pe.upc.vacappbackend.iam.interfaces.rest.resources.UserResource;

public class CampaignResourceFromEntityAssembler {
    public static CampaignResource toResourceFromEntity(Campaign entity) {
        return new CampaignResource(
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            entity.getStartDate(),
            entity.getEndDate(),
            entity.getStatus(),
            entity.getUserId()
        );
    }
}
