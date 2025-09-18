package pe.upc.vacappbackend.iam.domain.services;

import pe.upc.vacappbackend.iam.domain.model.aggregates.User;
import pe.upc.vacappbackend.iam.domain.model.commands.CreateUserCommand;
import pe.upc.vacappbackend.iam.domain.model.commands.DeleteUserCommand;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(CreateUserCommand command);
    void handle(DeleteUserCommand command);
}
