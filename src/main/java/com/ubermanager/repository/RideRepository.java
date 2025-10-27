package com.ubermanager.repository;

import com.ubermanager.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
    List<Ride> findByUserUserId(Long userId);
    List<Ride> findByDriverDriverId(Long driverId);
    List<Ride> findByStatus(Ride.RideStatus status);
    
    @Query("SELECT r FROM Ride r WHERE r.user.userId = :userId ORDER BY r.requestTime DESC")
    List<Ride> findUserRidesOrderByDate(@Param("userId") Long userId);
    
    @Query("SELECT r FROM Ride r WHERE r.driver.driverId = :driverId ORDER BY r.requestTime DESC")
    List<Ride> findDriverRidesOrderByDate(@Param("driverId") Long driverId);
    
    @Query("SELECT COUNT(r) FROM Ride r WHERE r.status = 'COMPLETED'")
    Long countCompletedRides();
    
    @Query("SELECT SUM(r.fare) FROM Ride r WHERE r.status = 'COMPLETED'")
    Double getTotalRevenue();
}
