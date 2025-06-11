package com.sandbox.proyecto.application.usecase.building.port.in;

import com.sandbox.proyecto.domain.model.building.Building;

import java.util.List;
import java.util.UUID;

public interface GetBuildingUseCase {

  List<Building> findAll();

  List<Building> findAllById(UUID id);

}
