package com.sandbox.proyecto.config;

import com.sandbox.proyecto.adapter.in.grpc.offices.OfficesServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class GrpcConfiguration implements CommandLineRunner {

  private final OfficesServiceImpl officesServiceImpl;

  private final GrpcConfigurationProperties grpcConfigurationProperties;

  @Override
  public void run(String... args) throws Exception {
    Server server = ServerBuilder
        .forPort(this.grpcConfigurationProperties.getPort())
        .addService(this.officesServiceImpl)
        .build()
        .start();

    log.info("gRPC server started on port {}", this.grpcConfigurationProperties.getPort());

    server.awaitTermination();
  }
}
