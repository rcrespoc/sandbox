package com.sandbox.proyecto.application.usecase.building.port.impl;

import com.sandbox.proyecto.application.usecase.building.port.out.BuildingPersistence;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BuildingUseCaseBaseImpl {

  protected final BuildingPersistence buildingPersistence;

}
