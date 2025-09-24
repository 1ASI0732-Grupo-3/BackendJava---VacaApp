package pe.upc.vacappbackend.campaignManagement.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.vacappbackend.campaignManagement.application.internal.commandservices.CampaignCommandServiceImpl;
import pe.upc.vacappbackend.campaignManagement.application.internal.queryservices.CampaignQueryServiceImpl;
import pe.upc.vacappbackend.campaignManagement.domain.model.aggregates.Campaign;
import pe.upc.vacappbackend.campaignManagement.domain.model.commands.CreateCampaignCommand;
import pe.upc.vacappbackend.campaignManagement.domain.model.commands.DeleteCampaignCommand;
import pe.upc.vacappbackend.campaignManagement.domain.model.queries.GetCampaignByIdQuery;
import pe.upc.vacappbackend.campaignManagement.domain.model.queries.GetCampaignsByUserIdQuery;
import pe.upc.vacappbackend.campaignManagement.interfaces.rest.resources.CampaignResource;
import pe.upc.vacappbackend.campaignManagement.interfaces.rest.resources.CreateCampaignResource;
import pe.upc.vacappbackend.campaignManagement.interfaces.rest.transform.CampaignResourceFromEntityAssembler;
import pe.upc.vacappbackend.campaignManagement.interfaces.rest.transform.CreateCampaignCommandFromResourceAssembler;
import pe.upc.vacappbackend.iam.application.internal.commandservices.UserCommandServiceImpl;
import pe.upc.vacappbackend.iam.application.internal.queryservices.UserQueryServiceImpl;
import pe.upc.vacappbackend.iam.domain.model.aggregates.User;
import pe.upc.vacappbackend.iam.domain.model.commands.DeleteUserCommand;
import pe.upc.vacappbackend.iam.domain.model.queries.GetUserByIdQuery;
import pe.upc.vacappbackend.iam.interfaces.rest.resources.CreateUserResource;
import pe.upc.vacappbackend.iam.interfaces.rest.resources.UserResource;
import pe.upc.vacappbackend.iam.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import pe.upc.vacappbackend.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import pe.upc.vacappbackend.ranchManagment.domain.model.queries.GetStablesByUserIdQuery;
import pe.upc.vacappbackend.ranchManagment.interfaces.rest.resources.Resource_Stable;
import pe.upc.vacappbackend.ranchManagment.interfaces.rest.transform.ResourceStableFromEntityAssembler;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/campaigns", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Campaign", description = "Available Campaign Endpoints")
public class CampaignController {
    private final CampaignCommandServiceImpl campaignCommandService;
    private final CampaignQueryServiceImpl campaignQueryService;

    public CampaignController(CampaignCommandServiceImpl campaignCommandService, CampaignQueryServiceImpl campaignQueryService) {
        this.campaignCommandService = campaignCommandService;
        this.campaignQueryService = campaignQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a Campaign", description = "Create a Campaign")
    public ResponseEntity<Campaign> createCampaign(@RequestBody CreateCampaignResource resource) {
            var createCampaignCommand = CreateCampaignCommandFromResourceAssembler.toCommandFromResource(resource);
            var createdCampaign = campaignCommandService.handle(createCampaignCommand)
                    .orElseThrow(() -> new IllegalArgumentException("Error creating Campaign"));
            return ResponseEntity.status(201).body(createdCampaign);

    }

    @DeleteMapping("{campaignId}")
    @Operation(summary = "Delete a Campaign", description = "Delete a Campaign by id")
    public void deleteCampaign(@PathVariable Long campaignId) {
        var deleteCampaignCommand = new DeleteCampaignCommand(campaignId);
        campaignCommandService.handle(deleteCampaignCommand);
    }

    @GetMapping("{campaignId}")
    @Operation(summary = "Get Campaign by id", description = "Get Campaign by id")
    public ResponseEntity<CampaignResource> getCampaign(@PathVariable Long campaignId) {
        var query = new GetCampaignByIdQuery(campaignId);
        var campaignOptional = campaignQueryService.handle(query);
        if (campaignOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var campaignResource = CampaignResourceFromEntityAssembler.toResourceFromEntity(campaignOptional.get());
        return ResponseEntity.ok(campaignResource);
    }

    @GetMapping("user/{userId}")
    @Operation(summary = "Get Campaigns by userId", description = "Get Campaigns by userId")
    public ResponseEntity<List<CampaignResource>> getCampaignsByUserId(@PathVariable Long userId) {
        var query = new GetCampaignsByUserIdQuery(userId);
        var campaigns = campaignQueryService.handle(query);
        var campaignsResources = campaigns.stream()
                .map(CampaignResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(campaignsResources);
    }



}
