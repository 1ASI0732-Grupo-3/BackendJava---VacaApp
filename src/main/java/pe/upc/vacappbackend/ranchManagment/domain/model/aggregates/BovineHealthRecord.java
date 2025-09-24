package pe.upc.vacappbackend.ranchManagment.domain.model.aggregates;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import pe.upc.vacappbackend.ranchManagment.domain.model.commands.CreateBovineHealthRecordCommand;
import pe.upc.vacappbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.Date;

@Getter
@Setter
@Entity
public class BovineHealthRecord extends AuditableAbstractAggregateRoot<BovineHealthRecord> {
    private String name;
    private String gender;
    private Date birthDate;
    private String breed;
    private String bovineImg;
    @ManyToOne
    @JoinColumn(name = "stable_id")
    private Stable stable;

    public BovineHealthRecord() {
        this.name = "";
        this.gender = "";
        this.birthDate = new Date();
        this.breed = "";
        this.bovineImg = "";
        this.stable = new Stable();
    }

    public BovineHealthRecord(CreateBovineHealthRecordCommand command, Stable stable) {
        this.name = command.name();
        this.gender = command.gender();
        this.birthDate = command.birthDate();
        this.breed = command.breed();
        this.bovineImg = command.bovineImg();
        this.stable = stable;
    }

}
