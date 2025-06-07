package com.sandbox.proyecto.adapter.out.repository.postgre.entity.office;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "offices")
@Data
public class OfficeEntity {

  @Id
  private UUID id;

  private String name;

  private String address;

}
