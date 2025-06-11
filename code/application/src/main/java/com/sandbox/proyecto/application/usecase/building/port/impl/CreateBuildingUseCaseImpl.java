package com.sandbox.proyecto.application.usecase.building.port.impl;

import com.sandbox.proyecto.application.usecase.building.port.in.CreateBuildingUseCase;
import com.sandbox.proyecto.application.usecase.building.port.out.BuildingPersistence;
import com.sandbox.proyecto.domain.model.building.Building;
import org.springframework.stereotype.Service;

@Service
public class CreateBuildingUseCaseImpl extends BuildingUseCaseBaseImpl implements CreateBuildingUseCase {

  public CreateBuildingUseCaseImpl(BuildingPersistence buildingPersistence) {
    super(buildingPersistence);
  }

  @Override
  public Building create(Building building) {
    return this.buildingPersistence.create(building);
  }
}
