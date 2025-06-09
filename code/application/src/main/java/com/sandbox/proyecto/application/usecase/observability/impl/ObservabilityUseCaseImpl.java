package com.sandbox.proyecto.application.usecase.observability.impl;

import com.sandbox.proyecto.application.usecase.observability.port.ObservabilityUseCase;
import com.sandbox.proyecto.application.usecase.observability.utils.Metrics;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.sandbox.proyecto.application.usecase.observability.utils.Metrics.SUBSCRIPTORS_TOTAL;

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

  @Override
  public <T> void initGaugeSubscribers(List<T> subscribers) {
    Gauge.builder(SUBSCRIPTORS_TOTAL.getName(), subscribers, List::size)
        .description("Total number of subscribers")
        .register(this.meterRegistry);
  }

  @Override
  public Timer.Sample startTimer() {
    return Timer.start(this.meterRegistry);
  }

  @Override
  public void stopTimer(Timer.Sample sample, Metrics metricName) {
    sample.stop(this.meterRegistry.timer(metricName.getName()));
  }
}
