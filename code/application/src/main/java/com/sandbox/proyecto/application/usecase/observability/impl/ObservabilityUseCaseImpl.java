package com.sandbox.proyecto.application.usecase.observability.impl;

import com.sandbox.proyecto.application.usecase.observability.port.ObservabilityUseCase;
import com.sandbox.proyecto.application.usecase.observability.utils.Metrics;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ObservabilityUseCaseImpl implements ObservabilityUseCase {

  private final MeterRegistry meterRegistry;

  @Override
  public void sendMetric(Metrics metricName) {
    this.meterRegistry.counter(metricName.getName()).increment();
  }

  @Override
  public Timer getTimer(Metrics metricName) {
    return this.meterRegistry.timer(metricName.getName());
  }
}
