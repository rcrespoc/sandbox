package com.sandbox.proyecto.adapter.out.persistence;

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

  @Override
  public List<Office> getOffices() {
    return this.officeRepository.findAll()
        .stream().map(this.officeMapper::toOffice)
        .toList();
  }

  @Override
  public Office createOffice(Office office) {
    final OfficeEntity officeEntity = this.officeMapper.toOfficeEntity(office);
    return this.officeMapper.toOffice(this.officeRepository.save(officeEntity));
  }
}
