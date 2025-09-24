package pe.upc.vacappbackend.iam.domain.model.aggregates;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import pe.upc.vacappbackend.iam.domain.model.commands.CreateUserCommand;
import pe.upc.vacappbackend.iam.domain.model.valueobjects.Role;
import pe.upc.vacappbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Getter
@Setter
@Entity
public class User extends AuditableAbstractAggregateRoot<User> {
    private String username;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
        this.username = "";
        this.password = "";
        this.email = "";
        this.role = Role.USER; // Default role
    }

    public User(CreateUserCommand command) {
        if (command == null) {
            throw new IllegalArgumentException("CreateUserCommand cannot be null.");
        }
        this.username = command.username();
        this.password = command.password();
        this.email = command.email();
        this.role = command.role();
    }
}
