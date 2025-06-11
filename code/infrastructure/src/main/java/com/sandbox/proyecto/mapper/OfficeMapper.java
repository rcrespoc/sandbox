package com.sandbox.proyecto.mapper;

import com.sandbox.proyecto.adapter.out.repository.postgre.entity.office.OfficeEntity;
import com.sandbox.proyecto.domain.model.offices.Office;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.openapitools.model.OfficeRequestDTO;
import org.openapitools.model.OfficeResponseDTO;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {
    BuildingMapper.class
})
public interface OfficeMapper {

  OfficeResponseDTO toOfficeResponseDTO(Office office);

  Office toOffice(OfficeEntity officeEntity);

  @Mapping(target = "id", source = ".", qualifiedByName = "mapId")
  @Mapping(target = "buildingId", source = ".", qualifiedByName = "mapBuildingId")
  Office toOffice(OfficeRequestDTO officeRequestDTO, @Context UUID buildingId);

  OfficeEntity toOfficeEntity(Office office);

  @Named("mapId")
  default UUID mapId(OfficeRequestDTO officeRequestDTO) {
    return UUID.randomUUID();
  }

  @Named("mapBuildingId")
  default UUID mapBuildingId(OfficeRequestDTO officeRequestDTO, @Context UUID buildingId) {
    return buildingId;
  }

  com.sandbox.proyecto.Office toOfficeGrpc(Office office);

}
