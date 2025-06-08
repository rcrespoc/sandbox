package com.sandbox.proyecto.application.usecase.observability.port;

import com.sandbox.proyecto.application.usecase.observability.utils.Metrics;
import io.micrometer.core.instrument.Timer;

public interface ObservabilityUseCase {

  void sendMetric(Metrics metricName);

  Timer getTimer(Metrics metricName);

}
