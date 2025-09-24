package pe.upc.vacappbackend.ranchManagment.domain.model.commands;

import pe.upc.vacappbackend.ranchManagment.domain.model.aggregates.Stable;

import java.util.Date;

public record CreateBovineHealthRecordCommand(
      String name,
      String gender,
      Date birthDate,
      String breed,
      String bovineImg,
      Long stableId
) {

}
