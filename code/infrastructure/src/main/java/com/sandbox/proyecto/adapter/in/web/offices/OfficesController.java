package com.sandbox.proyecto.adapter.in.web.offices;

import com.sandbox.proyecto.application.usecase.offices.port.in.CreateOfficeUseCase;
import com.sandbox.proyecto.application.usecase.offices.port.in.GetOfficesUseCase;
import com.sandbox.proyecto.domain.model.offices.Office;
import com.sandbox.proyecto.mapper.OfficeMapper;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.OfficeApi;
import org.openapitools.model.OfficeRequestDTO;
import org.openapitools.model.OfficeResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class OfficesController implements OfficeApi {

  private final GetOfficesUseCase getOfficesUseCase;

  private final CreateOfficeUseCase createOfficeUseCase;

  private final OfficeMapper officeMapper;

  @Override
  public ResponseEntity<List<OfficeResponseDTO>> getOffice() {
    final List<OfficeResponseDTO> officeResponseDTOList = this.getOfficesUseCase.getOffices()
        .stream().map(this.officeMapper::toOfficeResponseDTO)
        .toList();
    return ResponseEntity.ok(officeResponseDTOList);
  }

  @Override
  public ResponseEntity<OfficeResponseDTO> createOffice(UUID buildingId, OfficeRequestDTO officeRequestDTO) {
    final Office office = this.officeMapper.toOffice(officeRequestDTO, buildingId);
    final Office createdOffice = this.createOfficeUseCase.createOffice(office);
    return ResponseEntity.ok(this.officeMapper.toOfficeResponseDTO(createdOffice));
  }
}
