package com.sandbox.proyecto.domain.model.building;

import com.sandbox.proyecto.domain.model.offices.Office;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Building {

  private UUID id;

  private String name;

  private String address;

  private List<Office> offices;

  public void addOffice(Office office) {
    this.offices.add(office);
  }

}
