package com.sandbox.proyecto.adapter.out.repository.postgre.entity.building;

import com.sandbox.proyecto.adapter.out.repository.postgre.entity.office.OfficeEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
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

  @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OfficeEntity> offices = new ArrayList<>();

  public void addOffice(OfficeEntity officeEntity) {
    this.offices.add(officeEntity);
    officeEntity.setBuilding(this);
  }

}
