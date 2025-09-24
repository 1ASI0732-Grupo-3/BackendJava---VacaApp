package pe.upc.vacappbackend.ranchManagment.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.vacappbackend.iam.application.internal.commandservices.UserCommandServiceImpl;
import pe.upc.vacappbackend.iam.application.internal.queryservices.UserQueryServiceImpl;
import pe.upc.vacappbackend.iam.domain.model.aggregates.User;
import pe.upc.vacappbackend.iam.domain.model.commands.DeleteUserCommand;
import pe.upc.vacappbackend.iam.domain.model.queries.GetUserByIdQuery;
import pe.upc.vacappbackend.iam.interfaces.rest.resources.CreateUserResource;
import pe.upc.vacappbackend.iam.interfaces.rest.resources.UserResource;
import pe.upc.vacappbackend.iam.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import pe.upc.vacappbackend.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import pe.upc.vacappbackend.ranchManagment.application.internal.commandservices.BHRCommandServiceImpl;
import pe.upc.vacappbackend.ranchManagment.application.internal.queryservices.BHRQueryServiceImpl;
import pe.upc.vacappbackend.ranchManagment.domain.model.aggregates.BovineHealthRecord;
import pe.upc.vacappbackend.ranchManagment.domain.model.commands.DeleteBovineHealthRecordCommand;
import pe.upc.vacappbackend.ranchManagment.domain.model.queries.GetBHRByIdQuery;
import pe.upc.vacappbackend.ranchManagment.domain.model.queries.GetBHRsByStableIdQuery;
import pe.upc.vacappbackend.ranchManagment.domain.model.queries.GetStablesByUserIdQuery;
import pe.upc.vacappbackend.ranchManagment.interfaces.rest.resources.CreateBHRResource;
import pe.upc.vacappbackend.ranchManagment.interfaces.rest.resources.Resource_BHR;
import pe.upc.vacappbackend.ranchManagment.interfaces.rest.resources.Resource_Stable;
import pe.upc.vacappbackend.ranchManagment.interfaces.rest.transform.CreateBHRCommandFromResourceAssembler;
import pe.upc.vacappbackend.ranchManagment.interfaces.rest.transform.ResourceBHRFromEntityAssembler;
import pe.upc.vacappbackend.ranchManagment.interfaces.rest.transform.ResourceStableFromEntityAssembler;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/bhr", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Bovine Health Record", description = "Available Bovine Health Record Endpoints")
public class BHRController {
    private final BHRCommandServiceImpl bhrCommandService;
    private final BHRQueryServiceImpl bhrQueryService;

    public BHRController(BHRCommandServiceImpl bhrCommandService, BHRQueryServiceImpl bhrQueryService) {
        this.bhrCommandService = bhrCommandService;
        this.bhrQueryService = bhrQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a Bovine Health Record", description = "Create a Bovine Health Record")
    public ResponseEntity<BovineHealthRecord> createBovineHealthRecord(@RequestBody CreateBHRResource resource) {
            var createBHRCommand = CreateBHRCommandFromResourceAssembler.toCommandFromResource(resource);
            var createdBHR = bhrCommandService.handle(createBHRCommand)
                    .orElseThrow(() -> new IllegalArgumentException("Error creating BovineHealthRecord"));
            return ResponseEntity.status(201).body(createdBHR);

    }

    @DeleteMapping("{bhrId}")
    @Operation(summary = "Delete a BovineHealthRecord", description = "Delete a BovineHealthRecord by id")
    public void deleteBHRbyId(@PathVariable Long bhrId) {
        var deleteBHRCommand = new DeleteBovineHealthRecordCommand(bhrId);
        bhrCommandService.handle(deleteBHRCommand);
    }

    @GetMapping("{bhrId}")
    @Operation(summary = "Get BovineHealthRecord by id", description = "Get BovineHealthRecord by id")
    public ResponseEntity<Resource_BHR> getBHRbyId(@PathVariable Long bhrId) {
        var query = new GetBHRByIdQuery(bhrId);
        var bhrOptional = bhrQueryService.handle(query);
        if (bhrOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var userResource = ResourceBHRFromEntityAssembler.toResourceFromEntity(bhrOptional.get());
        return ResponseEntity.ok(userResource);
    }

    @GetMapping("stable/{stableId}")
    @Operation(summary = "Get BovineHealthRecords by stableId", description = "Get BovineHealthRecords by stableId")
    public ResponseEntity<List<Resource_BHR>> getBHRsByStableId(@PathVariable Long stableId) {
        var query = new GetBHRsByStableIdQuery(stableId);
        var BHRs = bhrQueryService.handle(query);
        var bhrResources = BHRs.stream()
                .map(ResourceBHRFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(bhrResources);
    }

}
