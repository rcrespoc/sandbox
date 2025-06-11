package com.sandbox.proyecto.adapter.out.repository.postgre.port.building;

import com.sandbox.proyecto.adapter.out.repository.postgre.entity.building.BuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BuildingRepository extends JpaRepository<BuildingEntity, UUID> {

  List<BuildingEntity> findAllById(UUID uuid);

}
