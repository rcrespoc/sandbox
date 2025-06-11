package com.sandbox.proyecto.application.usecase.building.port.out;

import com.sandbox.proyecto.domain.model.building.Building;

import java.util.List;
import java.util.UUID;

public interface BuildingPersistence {

  List<Building> findAll();

  Building create(Building building);

  List<Building> findAllById(UUID id);

}
