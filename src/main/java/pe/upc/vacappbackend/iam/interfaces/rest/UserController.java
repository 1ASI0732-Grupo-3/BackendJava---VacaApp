package pe.upc.vacappbackend.iam.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/users", produces = APPLICATION_JSON_VALUE)
@Tag(name = "User", description = "Available User Endpoints")
public class UserController {
    private final UserCommandServiceImpl userCommandService;
    private final UserQueryServiceImpl userQueryService;

    public UserController(UserCommandServiceImpl userCommandService, UserQueryServiceImpl userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a User", description = "Create a User")
    public ResponseEntity<User> createUser(@RequestBody CreateUserResource resource) {
            var createUserCommand = CreateUserCommandFromResourceAssembler.toCommandFromResource(resource);
            var createdUser = userCommandService.handle(createUserCommand)
                    .orElseThrow(() -> new IllegalArgumentException("Error creating user"));
            return ResponseEntity.status(201).body(createdUser);

    }

    @DeleteMapping("{userId}")
    @Operation(summary = "Delete a User", description = "Delete a User by id")
    public void deleteUser(@PathVariable Long userId) {
        var deleteUserCommand = new DeleteUserCommand(userId);
        userCommandService.handle(deleteUserCommand);
    }

    @GetMapping("{userId}")
    @Operation(summary = "Get User by id", description = "Get User by id")
    public ResponseEntity<UserResource> getUser(@PathVariable Long userId) {
        var query = new GetUserByIdQuery(userId);
        var userOptional = userQueryService.handle(query);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(userOptional.get());
        return ResponseEntity.ok(userResource);
    }

}
