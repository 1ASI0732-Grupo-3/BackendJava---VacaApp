package pe.upc.vacappbackend.campaignManagement.domain.model.aggregates;

import jakarta.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

import pe.upc.vacappbackend.campaignManagement.domain.model.commands.CreateCampaignCommand;
import pe.upc.vacappbackend.campaignManagement.domain.model.valueobjects.C_Status;
import pe.upc.vacappbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class   Campaign  extends AuditableAbstractAggregateRoot<Campaign > {
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private C_Status status;
    private Long userId;

    public Campaign() {
        this.name = "Default Campaign";
        this.description = "This is a default campaign description.";
        this.startDate = LocalDate.now();
        this.endDate = LocalDate.now().plusMonths(1);
        this.status = C_Status.PLANNED;
        this.userId = 0L;
    }

    public Campaign(CreateCampaignCommand command) {
        this.name = command.name();
        this.description = command.description();
        this.startDate = command.startDate();
        this.endDate = command.endDate();
        this.status = command.status();
        this.userId = command.userId();
    }

}
