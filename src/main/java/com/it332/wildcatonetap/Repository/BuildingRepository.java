package com.it332.wildcatonetap.Repository;

import com.it332.wildcatonetap.Entity.BuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<BuildingEntity, Long> {
    List<BuildingEntity> findAll();  // To fetch all buildings and their coordinates
}

