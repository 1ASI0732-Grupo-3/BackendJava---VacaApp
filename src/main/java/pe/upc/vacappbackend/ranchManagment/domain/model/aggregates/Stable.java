package pe.upc.vacappbackend.ranchManagment.domain.model.aggregates;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import pe.upc.vacappbackend.ranchManagment.domain.model.commands.CreateStableCommand;
import pe.upc.vacappbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Getter
@Setter
@Entity
public class Stable extends AuditableAbstractAggregateRoot<Stable> {
    private String name;
    @Column(name = "max_limit")
    private Integer maxLimit;
    private String location;
    private Long userId;

    public Stable() {
        this.name = "";
        this.maxLimit = 0;
        this.location = "";
        this.userId = 0L;
    }

    public Stable(CreateStableCommand command) {
        this.name = command.name();
        this.maxLimit = command.limit();
        this.location = command.location();
        this.userId = command.userId();
    }
}
