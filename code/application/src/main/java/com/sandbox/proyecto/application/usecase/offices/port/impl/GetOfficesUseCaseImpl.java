package com.sandbox.proyecto.application.usecase.offices.port.impl;

import com.sandbox.proyecto.application.usecase.observability.port.ObservabilityUseCase;
import com.sandbox.proyecto.application.usecase.offices.port.in.GetOfficesUseCase;
import com.sandbox.proyecto.application.usecase.offices.port.out.OfficesPersistence;
import com.sandbox.proyecto.domain.model.offices.Office;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sandbox.proyecto.application.usecase.observability.utils.Metrics.GET_OFFICES;

@RequiredArgsConstructor
@Service
public class GetOfficesUseCaseImpl implements GetOfficesUseCase {

  private final OfficesPersistence officesPersistence;

  private final ObservabilityUseCase observabilityUseCase;

  @Override
  public List<Office> getOffices() {
    this.observabilityUseCase.sendMetric(GET_OFFICES);
    return this.officesPersistence.getOffices();
  }

}
