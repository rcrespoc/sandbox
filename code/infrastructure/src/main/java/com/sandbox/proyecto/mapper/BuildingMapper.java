package com.sandbox.proyecto.mapper;

import com.sandbox.proyecto.adapter.out.repository.postgre.entity.building.BuildingEntity;
import com.sandbox.proyecto.domain.model.building.Building;
import org.mapstruct.Mapper;
import org.openapitools.model.BuildingRequestDTO;
import org.openapitools.model.BuildingResponseDTO;

@Mapper(componentModel = "spring")
public interface BuildingMapper {

  BuildingEntity toEntity(Building building);

  Building toDomain(BuildingEntity buildingEntity);

  BuildingResponseDTO toResponseDTO(Building building);

  // TODO: Add building id into offices and generate id into building
  Building toDomain(BuildingRequestDTO buildingResponseDTO);

  com.sandbox.proyecto.Building toGrpc(Building building);
}
