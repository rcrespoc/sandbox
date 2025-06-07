package com.sandbox.proyecto.adapter.in.web.controller;

import com.sandbox.proyecto.application.AppConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  private final AppConfig appConfig;

  public TestController(AppConfig appConfig) {
    this.appConfig = appConfig;
  }

  @RequestMapping(
      method = RequestMethod.GET,
      value = "/v1/test",
      produces = { "application/json" }
  )
  public String test() {
    System.out.println(this.appConfig.getProperty());
    return this.appConfig.getProperty();
  }

}
