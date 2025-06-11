package com.sandbox.proyecto.application.usecase.building.port.impl;

import com.sandbox.proyecto.application.usecase.building.port.in.GetBuildingUseCase;
import com.sandbox.proyecto.application.usecase.building.port.out.BuildingPersistence;
import com.sandbox.proyecto.domain.model.building.Building;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GetBuildingUseCaseImpl extends BuildingUseCaseBaseImpl implements GetBuildingUseCase {

  public GetBuildingUseCaseImpl(BuildingPersistence buildingPersistence) {
    super(buildingPersistence);
  }

  @Override
  public List<Building> findAll() {
    return this.buildingPersistence.findAll();
  }

  @Override
  public List<Building> findAllById(UUID id) {
    return this.buildingPersistence.findAllById(id);
  }
}
