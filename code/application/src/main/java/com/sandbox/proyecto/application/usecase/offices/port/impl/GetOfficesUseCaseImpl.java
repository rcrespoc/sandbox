package com.sandbox.proyecto.application.usecase.offices.port.impl;

import com.sandbox.proyecto.application.usecase.offices.port.in.GetOfficesUseCase;
import com.sandbox.proyecto.domain.model.offices.Office;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetOfficesUseCaseImpl implements GetOfficesUseCase {

  @Override
  public List<Office> getOffices() {
    return List.of();
  }

}
