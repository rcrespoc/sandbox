package com.sandbox.proyecto.adapter.in.grpc.offices;

import com.sandbox.proyecto.Empty;
import com.sandbox.proyecto.Office;
import com.sandbox.proyecto.OfficeServiceGrpc;
import com.sandbox.proyecto.application.usecase.observability.port.ObservabilityUseCase;
import io.grpc.stub.ServerCallStreamObserver;
import io.grpc.stub.StreamObserver;
import io.micrometer.core.instrument.Timer;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.sandbox.proyecto.application.usecase.observability.utils.Metrics.*;

@RequiredArgsConstructor
@Component
@Slf4j
public class OfficesServiceImpl extends OfficeServiceGrpc.OfficeServiceImplBase {

  private final Map<Integer, StreamObserver<Office>> subscribers = new HashMap<>();

  private final ObservabilityUseCase observabilityUseCase;

  @Override
  public void listenOffices(Empty request, StreamObserver<Office> responseObserver) {
    Timer.Sample sample = this.observabilityUseCase.startTimer();
    ServerCallStreamObserver<Office> serverCallStreamObserver = (ServerCallStreamObserver<Office>) responseObserver;
    serverCallStreamObserver.setOnCancelHandler(() -> {
      this.observabilityUseCase.stopTimer(sample, SUBSCRIPTION_DURATION);
      log.warn("Client cancelled the request");
      StreamObserver<Office> observer = this.subscribers.get(responseObserver.hashCode());
      this.subscribers.remove(observer.hashCode());
      this.observabilityUseCase.sendMetric(SUBSCRIBER_CANCEL, Map.of("ID", String.valueOf(observer.hashCode())));
    });

    this.subscribers.put(responseObserver.hashCode(), responseObserver);
    log.info("New subscriber added. Total subscribers: {}", this.subscribers.size());
    this.observabilityUseCase.sendMetric(SUBSCRIBER, Map.of("ID", String.valueOf(responseObserver.hashCode())));
  }

  public void notifySubscribers(Office office) {
    this.subscribers.values().forEach(subscriber -> subscriber.onNext(office));
    this.observabilityUseCase.sendMetric(SEND_GRPC_EVENT, Map.of());
  }

  @PostConstruct
  public void init() {
    this.observabilityUseCase.initGaugeSubscribers(List.of(subscribers.values()));
  }
}
