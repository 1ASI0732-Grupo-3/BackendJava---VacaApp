package pe.upc.vacappbackend.iam.domain.model.commands;

import pe.upc.vacappbackend.iam.domain.model.valueobjects.Role;

public record CreateUserCommand(
    String username,
    String password,
    String email,
    Role role
) {
    public CreateUserCommand {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or blank.");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank.");
        }
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null.");
        }
    }
}
