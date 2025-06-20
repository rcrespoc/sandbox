package com.sandbox.proyecto.mapper;

import com.sandbox.proyecto.Office;
import com.sandbox.proyecto.adapter.out.repository.postgre.entity.building.BuildingEntity;
import com.sandbox.proyecto.domain.model.building.Building;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.openapitools.model.BuildingRequestDTO;
import org.openapitools.model.BuildingResponseDTO;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {
    OfficeMapper.class
})
public interface BuildingMapper {

  BuildingEntity toEntity(Building building);

  Building toDomain(BuildingEntity buildingEntity);

  BuildingResponseDTO toResponseDTO(Building building);

  @Mapping(target = "id", source = ".", qualifiedByName = "mapId")
  Building toDomain(BuildingRequestDTO buildingResponseDTO, @Context UUID buildingId);

  default com.sandbox.proyecto.Building toGrpc(Building building, OfficeMapper officeMapper) {
    com.sandbox.proyecto.Building.Builder buildingBuilder = com.sandbox.proyecto.Building.newBuilder();
    buildingBuilder.setId(building.getId().toString());
    buildingBuilder.setName(building.getName());
    buildingBuilder.setAddress(building.getAddress());
    buildingBuilder.addAllOffices(building.getOffices().stream().map(officeMapper::toOfficeGrpc).toList());
    return buildingBuilder.build();
  }

  @Named("mapId")
  default UUID mapId(BuildingRequestDTO building, @Context UUID buildingId) {
    return buildingId;
  }

}
