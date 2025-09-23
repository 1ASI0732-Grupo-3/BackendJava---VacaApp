package pe.upc.vacappbackend.iam.interfaces.rest.resources;

import pe.upc.vacappbackend.iam.domain.model.valueobjects.Role;

public record UserResource(
    Long id,
    String username,
    String email,
    String password,
    Role role
) {
    public UserResource {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number");
        }
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or blank.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank.");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank.");
        }
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null.");
        }
    }
}
