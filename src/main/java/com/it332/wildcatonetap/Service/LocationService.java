package com.it332.wildcatonetap.Service;

import com.it332.wildcatonetap.Entity.LocationEntity;
import com.it332.wildcatonetap.Repository.LocationRepository;
import com.it332.wildcatonetap.Repository.UserRepository;
import com.it332.wildcatonetap.Entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private UserRepository userRepository;

    // Save the logged-in user's location
    public LocationEntity saveUserLocation(Double latitude, Double longitude) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserId = authentication.getName();  // Assuming `authentication.getName()` returns the idNumber

        // Fetch the logged-in user details from the repository using idNumber
        UserEntity user = userRepository.findByIdNumber(loggedInUserId);

        // Create a new LocationEntity object with the user's details and location coordinates
        LocationEntity locationEntity = new LocationEntity(user.getFullName(), user.getIdNumber(), latitude, longitude);

        // Save and return the new location entity
        return locationRepository.save(locationEntity);
    }

    // Get all locations
    public List<LocationEntity> getAllLocations() {
        return locationRepository.findAll();
    }

    // Get location by ID
    public LocationEntity getLocationById(Long id) {
        Optional<LocationEntity> location = locationRepository.findById(id);
        return location.orElse(null);
    }

    // Update location by ID
    public LocationEntity updateLocation(Long id, Double latitude, Double longitude) {
        Optional<LocationEntity> existingLocation = locationRepository.findById(id);
        if (existingLocation.isPresent()) {
            LocationEntity location = existingLocation.get();
            location.setLatitude(latitude);
            location.setLongitude(longitude);
            return locationRepository.save(location);
        } else {
            return null;
        }
    }

    // Delete location by ID
    public boolean deleteLocation(Long id) {
        Optional<LocationEntity> location = locationRepository.findById(id);
        if (location.isPresent()) {
            locationRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
