package com.sandbox.proyecto.domain.model.offices;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Office {

  private UUID id;

  private String name;

  private UUID buildingId;

}
