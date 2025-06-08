package com.sandbox.proyecto.config;

import com.sandbox.proyecto.adapter.in.grpc.offices.OfficesServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GrpcConfiguration implements CommandLineRunner {

  private final OfficesServiceImpl officesServiceImpl;

  @Override
  public void run(String... args) throws Exception {
    Server server = ServerBuilder
        .forPort(6565)
        .addService(this.officesServiceImpl)
        .build()
        .start();

    server.awaitTermination();
  }
}
