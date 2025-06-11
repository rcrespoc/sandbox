package com.sandbox.proyecto.adapter.out.repository.postgre.entity.building;

import com.sandbox.proyecto.adapter.out.repository.postgre.entity.office.OfficeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "buildings")
@Data
public class BuildingEntity {

  @Id
  private UUID id;

  private String name;

  private String address;

  private List<OfficeEntity> offices;

}
