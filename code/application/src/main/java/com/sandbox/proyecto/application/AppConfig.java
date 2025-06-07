package com.sandbox.proyecto.application;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sandbox")
@Data
public class AppConfig {

  private String property;

}
