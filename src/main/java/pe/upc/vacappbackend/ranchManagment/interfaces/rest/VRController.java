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
import pe.upc.vacappbackend.ranchManagment.application.internal.commandservices.VRCommandServiceImpl;
import pe.upc.vacappbackend.ranchManagment.application.internal.queryservices.VRQueryServiceImpl;
import pe.upc.vacappbackend.ranchManagment.domain.model.aggregates.VaccinationRecord;
import pe.upc.vacappbackend.ranchManagment.domain.model.commands.DeleteVaccinationRecordCommand;
import pe.upc.vacappbackend.ranchManagment.domain.model.queries.GetVaccinationRecordByBHRIdQuery;
import pe.upc.vacappbackend.ranchManagment.interfaces.rest.resources.CreateVRResource;
import pe.upc.vacappbackend.ranchManagment.interfaces.rest.resources.Resource_VR;
import pe.upc.vacappbackend.ranchManagment.interfaces.rest.transform.CreateVRCommandFromResourceAssembler;
import pe.upc.vacappbackend.ranchManagment.interfaces.rest.transform.ResourceVRFromEntityAssembler;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/vr", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Vaccination Record", description = "Available Vaccination Record Endpoints")
public class VRController {
    private final VRCommandServiceImpl vrCommandService;
    private final VRQueryServiceImpl vrQueryService;

    public VRController(VRCommandServiceImpl vrCommandService, VRQueryServiceImpl vrQueryService) {
        this.vrCommandService = vrCommandService;
        this.vrQueryService = vrQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a VaccinationRecord", description = "Create a Vaccination Record")
    public ResponseEntity<VaccinationRecord> createVaccinationRecord(@RequestBody CreateVRResource resource) {
            var createVRCommand = CreateVRCommandFromResourceAssembler.toCommandFromResource(resource);
            var createdVR = vrCommandService.handle(createVRCommand)
                    .orElseThrow(() -> new IllegalArgumentException("Error creating VaccinationRecord"));
            return ResponseEntity.status(201).body(createdVR);
    }

    @DeleteMapping("{vrId}")
    @Operation(summary = "Delete a VaccinationRecord", description = "Delete a VaccinationRecord by vrId")
    public void deleteVaccinationRecord(@PathVariable Long vrId) {
        var deleteVRCommand = new DeleteVaccinationRecordCommand(vrId);
        vrCommandService.handle(deleteVRCommand);
    }

    @GetMapping("bhr/{bhrId}")
    @Operation(summary = "Get VaccinationRecord by BHR Id", description = "Get VaccinationRecord by BHR Id")
    public ResponseEntity<Resource_VR> getUser(@PathVariable Long bhrId) {
        var query = new GetVaccinationRecordByBHRIdQuery(bhrId);
        var vrOptional = vrQueryService.handle(query);
        if (vrOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var userResource = ResourceVRFromEntityAssembler.toResourceFromEntity(vrOptional.get());
        return ResponseEntity.ok(userResource);
    }

}
