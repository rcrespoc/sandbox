package com.sandbox.proyecto.adapter.out.repository.postgre.entity.office;

import com.sandbox.proyecto.adapter.out.repository.postgre.entity.building.BuildingEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "office")
@Data
public class OfficeEntity {

  @Id
  private UUID id;

  private String name;

  @ManyToOne
  @JoinColumn(name = "building_id")
  private BuildingEntity building;

}
