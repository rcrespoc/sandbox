package com.sandbox.proyecto.mapper;

import com.sandbox.proyecto.adapter.out.repository.postgre.entity.office.OfficeEntity;
import com.sandbox.proyecto.domain.model.offices.Office;
import org.mapstruct.Mapper;
import org.openapitools.model.OfficeResponseDTO;

@Mapper(componentModel = "spring")
public interface OfficeMapper {

  OfficeResponseDTO toOfficeResponseDTO(Office office);

  Office toOffice(OfficeEntity officeEntity);

  OfficeEntity toOfficeEntity(Office office);

}
