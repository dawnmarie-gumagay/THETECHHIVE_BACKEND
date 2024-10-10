package com.it332.wildcatonetap.Controller;

import com.it332.wildcatonetap.Entity.BuildingEntity;
import com.it332.wildcatonetap.Service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buildings")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    // 1. Add a new building
    @PostMapping("/add")
    public ResponseEntity<BuildingEntity> addBuilding(@RequestBody BuildingEntity buildingEntity) {
        BuildingEntity savedBuilding = buildingService.addBuilding(buildingEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBuilding);
    }

    // 2. Get all buildings
    @GetMapping("/all")
    public ResponseEntity<List<BuildingEntity>> getAllBuildings() {
        List<BuildingEntity> buildings = buildingService.getAllBuildings();
        return ResponseEntity.ok(buildings);
    }
}
