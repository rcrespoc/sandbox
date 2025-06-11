package com.sandbox.proyecto.adapter.out.persistence;

import com.sandbox.proyecto.adapter.in.grpc.offices.BuildingServiceImpl;
import com.sandbox.proyecto.adapter.out.repository.postgre.entity.building.BuildingEntity;
import com.sandbox.proyecto.adapter.out.repository.postgre.entity.office.OfficeEntity;
import com.sandbox.proyecto.adapter.out.repository.postgre.port.office.OfficeRepository;
import com.sandbox.proyecto.application.usecase.building.port.out.BuildingPersistence;
import com.sandbox.proyecto.application.usecase.offices.port.out.OfficesPersistence;
import com.sandbox.proyecto.domain.model.building.Building;
import com.sandbox.proyecto.domain.model.offices.Office;
import com.sandbox.proyecto.mapper.BuildingMapper;
import com.sandbox.proyecto.mapper.OfficeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OfficesPersistenceAdapter implements OfficesPersistence {

  private final OfficeRepository officeRepository;

  private final OfficeMapper officeMapper;

  private final BuildingPersistence buildingPersistence;

  private final BuildingServiceImpl buildingService;

  private final BuildingMapper buildingMapper;

  @Override
  public List<Office> getOffices() {
    return this.officeRepository.findAll()
        .stream().map(this.officeMapper::toOffice)
        .toList();
  }

  @Override
  public Office createOffice(Office office) {
    final Building building = this.buildingPersistence.findById(office.getBuildingId());
    building.addOffice(office);
    final BuildingEntity buildingEntity = this.buildingMapper.toEntity(building);
    final OfficeEntity officeEntity = this.officeMapper.toOfficeEntity(office);
    officeEntity.setBuilding(buildingEntity);
    final OfficeEntity createdOffice = this.officeRepository.save(officeEntity);
    final Office officeSaved = this.officeMapper.toOffice(createdOffice);
    this.buildingService.notifySubscribers(this.buildingMapper.toGrpc(building));
    return officeSaved;
  }
}
