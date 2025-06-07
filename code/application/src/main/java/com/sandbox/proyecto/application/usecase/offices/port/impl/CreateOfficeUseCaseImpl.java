package com.sandbox.proyecto.application.usecase.offices.port.impl;

import com.sandbox.proyecto.application.usecase.offices.port.in.CreateOfficeUseCase;
import com.sandbox.proyecto.application.usecase.offices.port.out.OfficesPersistence;
import com.sandbox.proyecto.domain.model.offices.Office;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateOfficeUseCaseImpl implements CreateOfficeUseCase {

  private final OfficesPersistence officesPersistence;

  @Override
  public Office createOffice(Office office) {
    return this.officesPersistence.createOffice(office);
  }
}
