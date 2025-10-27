package com.ubermanager.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ubermanager.model.Driver;
import com.ubermanager.model.Ride;
import com.ubermanager.model.User;
import com.ubermanager.repository.RideRepository;

@Service
public class RideService {
    
    private final RideRepository rideRepository;
    private final UserService userService;
    private final DriverService driverService;
    
    @Autowired
    public RideService(RideRepository rideRepository, UserService userService, DriverService driverService) {
        this.rideRepository = rideRepository;
        this.userService = userService;
        this.driverService = driverService;
    }
    
    @Transactional
    public Ride requestRide(Ride ride) {
        User user = userService.getUserById(ride.getUser().getUserId());
        ride.setUser(user);
        ride.setStatus(Ride.RideStatus.REQUESTED);
        ride.setRequestTime(LocalDateTime.now());
        
        // Calculate fare based on distance (simplified)
        double baseFare = 50.0;
        double perKmRate = 15.0;
        Random random = new Random();
        double estimatedKm = 5 + random.nextInt(20); // Random 5-25 km
        ride.setFare(baseFare + (estimatedKm * perKmRate));
        
        return rideRepository.save(ride);
    }
    
    @Transactional
    public Ride acceptRide(Long rideId, Long driverId) {
        Ride ride = getRideById(rideId);
        Driver driver = driverService.getDriverById(driverId);
        
        if (ride.getStatus() != Ride.RideStatus.REQUESTED) {
            throw new RuntimeException("Ride cannot be accepted. Current status: " + ride.getStatus());
        }
        
        ride.setDriver(driver);
        ride.setStatus(Ride.RideStatus.ACCEPTED);
        return rideRepository.save(ride);
    }
    
    @Transactional
    public Ride startRide(Long rideId) {
        Ride ride = getRideById(rideId);
        
        if (ride.getStatus() != Ride.RideStatus.ACCEPTED) {
            throw new RuntimeException("Ride cannot be started. Current status: " + ride.getStatus());
        }
        
        ride.setStatus(Ride.RideStatus.IN_PROGRESS);
        ride.setStartTime(LocalDateTime.now());
        return rideRepository.save(ride);
    }
    
    @Transactional
    public Ride completeRide(Long rideId) {
        Ride ride = getRideById(rideId);
        
        if (ride.getStatus() != Ride.RideStatus.IN_PROGRESS) {
            throw new RuntimeException("Ride cannot be completed. Current status: " + ride.getStatus());
        }
        
        ride.setStatus(Ride.RideStatus.COMPLETED);
        ride.setEndTime(LocalDateTime.now());
        return rideRepository.save(ride);
    }
    
    @Transactional
    public Ride cancelRide(Long rideId) {
        Ride ride = getRideById(rideId);
        
        if (ride.getStatus() == Ride.RideStatus.COMPLETED) {
            throw new RuntimeException("Cannot cancel a completed ride");
        }
        
        ride.setStatus(Ride.RideStatus.CANCELLED);
        return rideRepository.save(ride);
    }
    
    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }
    
    public Ride getRideById(Long id) {
        return rideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ride not found with id: " + id));
    }
    
    public List<Ride> getRidesByUser(Long userId) {
        return rideRepository.findUserRidesOrderByDate(userId);
    }
    
    public List<Ride> getRidesByDriver(Long driverId) {
        return rideRepository.findDriverRidesOrderByDate(driverId);
    }
    
    public List<Ride> getRidesByStatus(Ride.RideStatus status) {
        return rideRepository.findByStatus(status);
    }
    
    public Long getTotalRidesCount() {
        return rideRepository.count();
    }
    
    public Long getCompletedRidesCount() {
        return rideRepository.countCompletedRides();
    }
    
    public Double getTotalRevenue() {
        Double revenue = rideRepository.getTotalRevenue();
        return revenue != null ? revenue : 0.0;
    }
}
