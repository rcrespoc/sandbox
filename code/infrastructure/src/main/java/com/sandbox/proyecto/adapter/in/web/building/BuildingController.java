package com.sandbox.proyecto.adapter.in.web.building;

import com.sandbox.proyecto.application.usecase.building.port.in.CreateBuildingUseCase;
import com.sandbox.proyecto.application.usecase.building.port.in.GetBuildingUseCase;
import com.sandbox.proyecto.domain.model.building.Building;
import com.sandbox.proyecto.mapper.BuildingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.api.BuildingApi;
import org.openapitools.model.BuildingRequestDTO;
import org.openapitools.model.BuildingResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BuildingController implements BuildingApi {

  private final GetBuildingUseCase getBuildingUseCase;

  private final CreateBuildingUseCase createBuildingUseCase;

  private final BuildingMapper buildingMapper;

  @Override
  public ResponseEntity<BuildingResponseDTO> createBuilding(BuildingRequestDTO buildingRequestDTO) {
    final Building building = this.buildingMapper.toDomain(buildingRequestDTO);
    final Building buildingCreated = this.createBuildingUseCase.create(building);
    return ResponseEntity.ok(this.buildingMapper.toResponseDTO(buildingCreated));
  }

  @Override
  public ResponseEntity<List<BuildingResponseDTO>> getBuilding() {
    final List<Building> buildings = this.getBuildingUseCase.findAll();
    final List<BuildingResponseDTO> buildingResponseDTOS =
        buildings.stream().map(this.buildingMapper::toResponseDTO).toList();
    return ResponseEntity.ok(buildingResponseDTOS);
  }

  @Override
  public ResponseEntity<List<BuildingResponseDTO>> getBuildingById(UUID buildingId) {
    final List<Building> buildings = this.getBuildingUseCase.findAllById(buildingId);
    final List<BuildingResponseDTO> buildingResponseDTOS =
        buildings.stream().map(this.buildingMapper::toResponseDTO).toList();
    return ResponseEntity.ok(buildingResponseDTOS);
  }
}
