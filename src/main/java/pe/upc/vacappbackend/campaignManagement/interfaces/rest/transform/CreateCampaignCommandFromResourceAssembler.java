package pe.upc.vacappbackend.campaignManagement.interfaces.rest.transform;

import pe.upc.vacappbackend.campaignManagement.domain.model.commands.CreateCampaignCommand;
import pe.upc.vacappbackend.campaignManagement.interfaces.rest.resources.CreateCampaignResource;
import pe.upc.vacappbackend.iam.domain.model.commands.CreateUserCommand;
import pe.upc.vacappbackend.iam.interfaces.rest.resources.CreateUserResource;

public class CreateCampaignCommandFromResourceAssembler {
    public static CreateCampaignCommand toCommandFromResource(CreateCampaignResource resource) {
        return new CreateCampaignCommand(
                resource.name(),
                resource.description(),
                resource.startDate(),
                resource.endDate(),
                resource.status(),
                resource.userId()
        );
    }
}
