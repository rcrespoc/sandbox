package com.sandbox.proyecto.adapter.out.persistence;

import com.sandbox.proyecto.application.usecase.offices.port.out.OfficesPersistence;
import com.sandbox.proyecto.domain.model.offices.Office;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OfficesPersistenceAdapter implements OfficesPersistence {

  @Override
  public List<Office> getOffices() {
    return List.of();
  }

}
