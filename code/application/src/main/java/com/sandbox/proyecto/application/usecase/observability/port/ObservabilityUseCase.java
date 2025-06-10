package com.sandbox.proyecto.application.usecase.observability.port;

import com.sandbox.proyecto.application.usecase.observability.utils.Metrics;
import io.micrometer.core.instrument.Timer;

import java.util.List;
import java.util.Map;

public interface ObservabilityUseCase {

  void sendMetric(Metrics metricName, Map<String, String> tags);

  void sendMetric(Metrics metricName);

  Timer getTimer(Metrics metricName);

  <T> void initGaugeSubscribers(List<T> subscribers);

  Timer.Sample startTimer();

  void stopTimer(Timer.Sample sample, Metrics metricName);

}
