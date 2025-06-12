package com.sandbox.proyecto.adapter.out.persistence;

import com.sandbox.proyecto.adapter.in.grpc.offices.BuildingServiceImpl;
import com.sandbox.proyecto.adapter.out.repository.postgre.entity.building.BuildingEntity;
import com.sandbox.proyecto.adapter.out.repository.postgre.port.building.BuildingRepository;
import com.sandbox.proyecto.application.usecase.building.port.out.BuildingPersistence;
import com.sandbox.proyecto.domain.model.building.Building;
import com.sandbox.proyecto.mapper.BuildingMapper;
import com.sandbox.proyecto.mapper.OfficeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BuildingPersistenceAdapter implements BuildingPersistence {

  private final BuildingRepository buildingRepository;

  private final BuildingMapper buildingMapper;

  private final BuildingServiceImpl buildingServiceImpl;

  private final OfficeMapper officeMapper;

  @Override
  public List<Building> findAll() {
    return this.buildingRepository.findAll()
        .stream().map(this.buildingMapper::toDomain)
        .toList();
  }

  @Override
  public Building create(Building building) {
    final BuildingEntity buildingEntity = this.buildingMapper.toEntity(building);
    buildingEntity.getOffices().forEach(officeEntity -> officeEntity.setBuilding(buildingEntity));
    final BuildingEntity createdBuilding = this.buildingRepository.save(buildingEntity);
    final Building buildingSaved = this.buildingMapper.toDomain(createdBuilding);
    this.buildingServiceImpl.notifySubscribers(this.buildingMapper.toGrpc(buildingSaved, this.officeMapper));
    return buildingSaved;
  }

  @Override
  public List<Building> findAllById(UUID id) {
    return this.buildingRepository.findAllById(id)
        .stream().map(this.buildingMapper::toDomain)
        .toList();
  }

  @Override
  public Building findById(UUID id) {
    return this.buildingRepository.findById(id)
        .map(this.buildingMapper::toDomain)
        .orElseThrow();
  }
}
