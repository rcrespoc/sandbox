package com.sandbox.proyecto.adapter.in.grpc.offices;

import com.sandbox.proyecto.Empty;
import com.sandbox.proyecto.Office;
import com.sandbox.proyecto.OfficeServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class OfficesServiceImpl extends OfficeServiceGrpc.OfficeServiceImplBase {

  private final List<StreamObserver<Office>> subscribers = new ArrayList<>();

  @Override
  public void listenOffices(Empty request, StreamObserver<Office> responseObserver) {
    this.subscribers.add(responseObserver);
  }

  public void notifySubscribers(Office office) {
    this.subscribers.forEach(subscriber -> subscriber.onNext(office));
  }
}
