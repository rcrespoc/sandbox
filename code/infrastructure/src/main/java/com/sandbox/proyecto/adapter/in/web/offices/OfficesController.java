package com.sandbox.proyecto.adapter.in.web.offices;

import com.sandbox.proyecto.application.usecase.offices.port.in.GetOfficesUseCase;
import com.sandbox.proyecto.mapper.OfficeMapper;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.OfficeApi;
import org.openapitools.model.OfficeResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OfficesController implements OfficeApi {

  private final GetOfficesUseCase getOfficesUseCase;

  private final OfficeMapper officeMapper;

  @Override
  public ResponseEntity<List<OfficeResponseDTO>> getOffice() {
    final List<OfficeResponseDTO> officeResponseDTOList = this.getOfficesUseCase.getOffices()
        .stream().map(this.officeMapper::toOfficeResponseDTO)
        .toList();
    return ResponseEntity.ok(officeResponseDTOList);
  }

}
