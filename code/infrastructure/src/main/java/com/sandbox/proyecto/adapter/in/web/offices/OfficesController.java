package com.sandbox.proyecto.adapter.in.web.offices;

import org.openapitools.api.OfficeApi;
import org.openapitools.model.OfficeResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OfficesController implements OfficeApi {

  @Override
  public ResponseEntity<List<OfficeResponseDTO>> getOffice() {
    return OfficeApi.super.getOffice();
  }

}
