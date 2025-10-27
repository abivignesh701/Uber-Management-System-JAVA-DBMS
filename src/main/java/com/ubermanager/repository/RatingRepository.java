package com.ubermanager.repository;

import com.ubermanager.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByDriverDriverId(Long driverId);
    Optional<Rating> findByRideRideId(Long rideId);
    
    @Query("SELECT AVG(r.rating) FROM Rating r WHERE r.driver.driverId = :driverId")
    Double getAverageRatingForDriver(@Param("driverId") Long driverId);
}
