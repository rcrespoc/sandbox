package com.sandbox.proyecto.application.usecase.observability.utils;

import lombok.Getter;

@Getter
public enum Metrics {

  CREATE_OFFICE("create_office"),
  CREATE_OFFICE_TIMER("create_office_timer"),
  GET_OFFICES("get_offices"),
  SEND_GRPC_EVENT("send_grpc_event"),
  SUBSCRIBER("subscriber"),
  SUBSCRIBER_CANCEL("subscriber_cancel");


  private final String name;

  Metrics(String name) {
    this.name = name;
  }

}
