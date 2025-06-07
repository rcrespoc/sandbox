package com.sandbox.proyecto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(scanBasePackages = "com.sandbox.proyecto")
@ConfigurationPropertiesScan("com.sandbox.proyecto")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
