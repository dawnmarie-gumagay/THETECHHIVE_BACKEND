package com.it332.wildcatonetap.Controller;

import com.it332.wildcatonetap.Entity.LocationEntity;
import com.it332.wildcatonetap.Service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    // 1. Save logged-in user's location
    @PostMapping("/user-location")
    public ResponseEntity<LocationEntity> saveUserLocation(@RequestParam Double latitude, @RequestParam Double longitude) {
        LocationEntity savedLocation = locationService.saveUserLocation(latitude, longitude);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLocation);
    }

    // 2. Get all locations
    @GetMapping
    public ResponseEntity<List<LocationEntity>> getAllLocations() {
        List<LocationEntity> locations = locationService.getAllLocations();
        return ResponseEntity.ok(locations);
    }

    // 3. Get a location by ID
    @GetMapping("/{id}")
    public ResponseEntity<LocationEntity> getLocationById(@PathVariable Long id) {
        LocationEntity location = locationService.getLocationById(id);
        if (location != null) {
            return ResponseEntity.ok(location);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 4. Update location by ID
    @PutMapping("/{id}")
    public ResponseEntity<LocationEntity> updateLocation(@PathVariable Long id, @RequestParam Double latitude, @RequestParam Double longitude) {
        LocationEntity updatedLocation = locationService.updateLocation(id, latitude, longitude);
        if (updatedLocation != null) {
            return ResponseEntity.ok(updatedLocation);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 5. Delete a location by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        boolean isDeleted = locationService.deleteLocation(id);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
