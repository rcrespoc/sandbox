package com.sandbox.proyecto.application.usecase.offices.port.out;

import com.sandbox.proyecto.domain.model.offices.Office;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OfficesPersistence {

  List<Office> getOffices();

}
