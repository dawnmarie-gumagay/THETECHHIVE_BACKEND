package com.it332.wildcatonetap.Repository;

import com.it332.wildcatonetap.Entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Long> {

    // Find locations by latitude and longitude
    List<LocationEntity> findByLatitudeAndLongitude(double latitude, double longitude);

    // Find locations by idNumber
    List<LocationEntity> findByIdNumber(String idNumber);
}
