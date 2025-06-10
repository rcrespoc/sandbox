package com.sandbox.proyecto.adapter.in.grpc.offices;

import com.sandbox.proyecto.Building;
import com.sandbox.proyecto.BuildingId;
import com.sandbox.proyecto.BuildingServiceGrpc;
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
public class BuildingServiceImpl extends BuildingServiceGrpc.BuildingServiceImplBase {

  private final Map<String, List<StreamObserver<Building>>> subscribers = new HashMap<>();

  private final ObservabilityUseCase observabilityUseCase;

  @Override
  public void listenBuildings(BuildingId request, StreamObserver<Building> responseObserver) {
    final Timer.Sample sample = this.observabilityUseCase.startTimer();
    ServerCallStreamObserver<Building> serverCallStreamObserver = (ServerCallStreamObserver<Building>) responseObserver;
    serverCallStreamObserver.setOnCancelHandler(() -> {
      this.observabilityUseCase.stopTimer(sample, SUBSCRIPTION_DURATION);
      log.warn("Client cancelled the request");
      this.removeBuildingSubscriber(request.getId(), responseObserver);
      this.observabilityUseCase.sendMetric(SUBSCRIBER_CANCEL, Map.of("Building", request.getId()));
    });

    this.addBuildingSubscriber(request.getId(), responseObserver);
    log.info("New subscriber added. Total subscribers: {}", this.subscribers.size());
    this.observabilityUseCase.sendMetric(SUBSCRIBER, Map.of("ID", String.valueOf(responseObserver.hashCode())));
  }

  public void notifySubscribers(Building building) {
    this.subscribers.get(building.getId()).forEach(subscriber -> subscriber.onNext(building));
    this.observabilityUseCase.sendMetric(SEND_GRPC_EVENT, Map.of());
  }

  @PostConstruct
  public void init() {
    this.observabilityUseCase.initGaugeSubscribers(List.of(subscribers.values()));
  }

  private void removeBuildingSubscriber(String id, StreamObserver<Building> responseObserver) {
    final List<StreamObserver<Building>> subscribers = this.subscribers.get(id);
    if (subscribers != null) {
      subscribers.remove(responseObserver);
      log.info("Subscriber removed from the building {}. Total subscribers: {}", id, this.subscribers.size());
      this.observabilityUseCase.sendMetric(SUBSCRIBER_CANCEL, Map.of("Building", id));
    }
  }

  private void addBuildingSubscriber(String id, StreamObserver<Building> responseObserver) {
    final List<StreamObserver<Building>> subscribers = this.subscribers.get(id);
    if (subscribers == null) {
      this.subscribers.put(id, new ArrayList<>(List.of(responseObserver)));
    } else {
      subscribers.add(responseObserver);
    }
    log.info("New subscriber added to the building {}. Total subscribers: {}", id, this.subscribers.size());
    this.observabilityUseCase.sendMetric(SUBSCRIBER, Map.of("Building", id));
  }
}
