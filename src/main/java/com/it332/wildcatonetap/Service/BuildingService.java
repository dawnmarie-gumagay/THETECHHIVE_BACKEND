package com.it332.wildcatonetap.Service;

import com.it332.wildcatonetap.Entity.BuildingEntity;
import com.it332.wildcatonetap.Repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    // Save new building details
    public BuildingEntity addBuilding(BuildingEntity buildingEntity) {
        return buildingRepository.save(buildingEntity);
    }

    // Get all buildings
    public List<BuildingEntity> getAllBuildings() {
        return buildingRepository.findAll();
    }
}
