package com.sandbox.proyecto.adapter.out.repository.postgre.port.office;

import com.sandbox.proyecto.adapter.out.repository.postgre.entity.office.OfficeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OfficeRepository extends JpaRepository<OfficeEntity, UUID> {
}
