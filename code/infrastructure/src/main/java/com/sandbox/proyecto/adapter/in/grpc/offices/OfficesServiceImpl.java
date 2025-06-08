package com.sandbox.proyecto.adapter.in.grpc.offices;

import com.sandbox.proyecto.Empty;
import com.sandbox.proyecto.Office;
import com.sandbox.proyecto.OfficeServiceGrpc;
import com.sandbox.proyecto.application.usecase.observability.port.ObservabilityUseCase;
import io.grpc.stub.ServerCallStreamObserver;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.sandbox.proyecto.application.usecase.observability.utils.Metrics.*;

@RequiredArgsConstructor
@Component
@Slf4j
public class OfficesServiceImpl extends OfficeServiceGrpc.OfficeServiceImplBase {

  private final List<StreamObserver<Office>> subscribers = new ArrayList<>();

  private final ObservabilityUseCase observabilityUseCase;

  @Override
  public void listenOffices(Empty request, StreamObserver<Office> responseObserver) {

    ServerCallStreamObserver<Office> serverCallStreamObserver = (ServerCallStreamObserver<Office>) responseObserver;
    serverCallStreamObserver.setOnCancelHandler(() -> {
      log.warn("Client cancelled the request");
      this.subscribers.remove(responseObserver);
      this.observabilityUseCase.sendMetric(SUBSCRIBER_CANCEL);
    });

    this.subscribers.add(responseObserver);
    log.info("New subscriber added. Total subscribers: {}", this.subscribers.size());
    this.observabilityUseCase.sendMetric(SUBSCRIBER);
  }

  public void notifySubscribers(Office office) {
    this.subscribers.forEach(subscriber -> subscriber.onNext(office));
    this.observabilityUseCase.sendMetric(SEND_GRPC_EVENT);
  }
}
