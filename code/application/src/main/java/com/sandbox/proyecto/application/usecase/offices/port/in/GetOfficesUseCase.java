package com.sandbox.proyecto.application.usecase.offices.port.in;

import com.sandbox.proyecto.domain.model.offices.Office;

import java.util.List;

public interface GetOfficesUseCase {

  List<Office> getOffices();

}
