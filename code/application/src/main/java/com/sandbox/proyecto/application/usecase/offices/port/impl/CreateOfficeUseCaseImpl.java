package com.sandbox.proyecto.application.usecase.offices.port.impl;

import com.sandbox.proyecto.application.usecase.observability.port.ObservabilityUseCase;
import com.sandbox.proyecto.application.usecase.offices.port.in.CreateOfficeUseCase;
import com.sandbox.proyecto.application.usecase.offices.port.out.OfficesPersistence;
import com.sandbox.proyecto.domain.model.offices.Office;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.sandbox.proyecto.application.usecase.observability.utils.Metrics.CREATE_OFFICE;
import static com.sandbox.proyecto.application.usecase.observability.utils.Metrics.CREATE_OFFICE_TIMER;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateOfficeUseCaseImpl implements CreateOfficeUseCase {

  private final OfficesPersistence officesPersistence;

  private final ObservabilityUseCase observabilityUseCase;

  @Override
  public Office createOffice(Office office) {
    final Timer timer = this.observabilityUseCase.getTimer(CREATE_OFFICE);
    return timer.record(() -> {
      try {
        final long sleepTime = (long) (Math.random() * 1000);
        Thread.sleep(sleepTime);
        log.info("Sleep time: {}", sleepTime);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      this.observabilityUseCase.sendMetric(CREATE_OFFICE_TIMER);
      return this.officesPersistence.createOffice(office);
    });
  }
}
