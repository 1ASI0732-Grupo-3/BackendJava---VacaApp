package pe.upc.vacappbackend.ranchManagment.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.vacappbackend.ranchManagment.application.internal.commandservices.StableCommandServiceImpl;
import pe.upc.vacappbackend.ranchManagment.application.internal.queryservices.StableQueryServiceImpl;
import pe.upc.vacappbackend.ranchManagment.domain.model.aggregates.Stable;
import pe.upc.vacappbackend.ranchManagment.domain.model.commands.DeleteStableCommand;
import pe.upc.vacappbackend.ranchManagment.domain.model.queries.GetStableByIdQuery;
import pe.upc.vacappbackend.ranchManagment.domain.model.queries.GetStablesByUserIdQuery;
import pe.upc.vacappbackend.ranchManagment.interfaces.rest.resources.CreateStableResource;
import pe.upc.vacappbackend.ranchManagment.interfaces.rest.resources.Resource_Stable;
import pe.upc.vacappbackend.ranchManagment.interfaces.rest.transform.CreateStableCommandFromResourceAssembler;
import pe.upc.vacappbackend.ranchManagment.interfaces.rest.transform.ResourceStableFromEntityAssembler;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/stables", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Stable", description = "Available Stable Endpoints")
public class StableController {
    private final StableCommandServiceImpl stableCommandService;
    private final StableQueryServiceImpl stableQueryService;

    public StableController(StableCommandServiceImpl stableCommandService, StableQueryServiceImpl stableQueryService) {
        this.stableCommandService = stableCommandService;
        this.stableQueryService = stableQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a Stable", description = "Create a Stable")
    public ResponseEntity<Stable> createStable(@RequestBody CreateStableResource resource) {
            var createStableCommand = CreateStableCommandFromResourceAssembler.toCommandFromResource(resource);
            var createdStable = stableCommandService.handle(createStableCommand)
                    .orElseThrow(() -> new IllegalArgumentException("Error creating Stable"));
            return ResponseEntity.status(201).body(createdStable);
    }

    @DeleteMapping("{stableId}")
    @Operation(summary = "Delete a Stable", description = "Delete a Stable by id")
    public void deleteStable(@PathVariable Long stableId) {
        var deleteStableCommand = new DeleteStableCommand(stableId);
        stableCommandService.handle(deleteStableCommand);
    }

    @GetMapping("{stableId}")
    @Operation(summary = "Get Stable by Id", description = "Get Stable by Id")
    public ResponseEntity<Resource_Stable> getStable(@PathVariable Long stableId) {
        var query = new GetStableByIdQuery(stableId);
        var stableOptional = stableQueryService.handle(query);
        if (stableOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var stableResource = ResourceStableFromEntityAssembler.toResourceFromEntity(stableOptional.get());
        return ResponseEntity.ok(stableResource);
    }

    @GetMapping("user/{userId}")
    @Operation(summary = "Get Stables by userId", description = "Get Stables by userId")
    public ResponseEntity<List<Resource_Stable>> getStablesByUserId(@PathVariable Long userId) {
        var query = new GetStablesByUserIdQuery(userId);
        var stables = stableQueryService.handle(query);
        var stableResources = stables.stream()
                .map(ResourceStableFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(stableResources);
    }

}
