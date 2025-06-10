package com.sandbox.proyecto.adapter.out.persistence;

import com.sandbox.proyecto.adapter.in.grpc.offices.BuildingServiceImpl;
import com.sandbox.proyecto.adapter.out.repository.postgre.entity.office.OfficeEntity;
import com.sandbox.proyecto.adapter.out.repository.postgre.port.office.OfficeRepository;
import com.sandbox.proyecto.application.usecase.offices.port.out.OfficesPersistence;
import com.sandbox.proyecto.domain.model.offices.Office;
import com.sandbox.proyecto.mapper.OfficeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OfficesPersistenceAdapter implements OfficesPersistence {

  private final OfficeRepository officeRepository;

  private final OfficeMapper officeMapper;

  private final BuildingServiceImpl officesServiceImpl;

  @Override
  public List<Office> getOffices() {
    return this.officeRepository.findAll()
        .stream().map(this.officeMapper::toOffice)
        .toList();
  }

  @Override
  public Office createOffice(Office office) {
    final OfficeEntity officeEntity = this.officeMapper.toOfficeEntity(office);
    final OfficeEntity createdOffice = this.officeRepository.save(officeEntity);
    final Office officeSaved = this.officeMapper.toOffice(createdOffice);
    this.officesServiceImpl.notifySubscribers(this.officeMapper.toOfficeGrpc(officeSaved));
    return officeSaved;
  }
}
