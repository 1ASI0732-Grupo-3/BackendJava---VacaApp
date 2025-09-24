package pe.upc.vacappbackend.ranchManagment.domain.model.aggregates;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import pe.upc.vacappbackend.ranchManagment.domain.model.commands.CreateVaccinationCommand;
import pe.upc.vacappbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import java.util.Date;

@Getter
@Setter
@Entity
public class VaccinationRecord extends AuditableAbstractAggregateRoot<VaccinationRecord> {
    private String name;
    private String vaccineType;
    private Date vaccineDate;
    private String vaccineImg;
    private Long bovineId;

    public VaccinationRecord(CreateVaccinationCommand command) {
        this.name = command.name();
        this.vaccineType = command.vaccineType();
        this.vaccineDate = command.vaccineDate();
        this.vaccineImg = command.vaccineImg();
        this.bovineId = command.bovineId();

    }
}
