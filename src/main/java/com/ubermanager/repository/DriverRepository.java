package com.ubermanager.repository;

import com.ubermanager.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findByEmail(String email);
    boolean existsByEmail(String email);
    
    @Query("SELECT d FROM Driver d ORDER BY d.ratingAvg DESC")
    List<Driver> findTopRatedDrivers();
}
