package com.ubermanager.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ubermanager.model.Rating;
import com.ubermanager.model.Ride;
import com.ubermanager.repository.RatingRepository;

@Service
public class RatingService {
    
    private final RatingRepository ratingRepository;
    private final RideService rideService;
    private final DriverService driverService;
    
    @Autowired
    public RatingService(RatingRepository ratingRepository, RideService rideService, DriverService driverService) {
        this.ratingRepository = ratingRepository;
        this.rideService = rideService;
        this.driverService = driverService;
    }
    
    @Transactional
    public Rating createRating(Rating rating) {
        Ride ride = rideService.getRideById(rating.getRide().getRideId());
        
        if (ride.getStatus() != Ride.RideStatus.COMPLETED) {
            throw new RuntimeException("Rating can only be given for completed rides");
        }
        
        if (ratingRepository.findByRideRideId(ride.getRideId()).isPresent()) {
            throw new RuntimeException("Rating already exists for this ride");
        }
        
        rating.setRide(ride);
        rating.setUser(ride.getUser());
        rating.setDriver(ride.getDriver());
        rating.setRatingDate(LocalDateTime.now());
        
        Rating savedRating = ratingRepository.save(rating);
        
        // Update driver's average rating
        driverService.updateDriverRating(ride.getDriver().getDriverId());
        
        return savedRating;
    }
    
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }
    
    public Rating getRatingById(Long id) {
        return ratingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rating not found with id: " + id));
    }
    
    public List<Rating> getRatingsByDriver(Long driverId) {
        return ratingRepository.findByDriverDriverId(driverId);
    }
    
    public Rating getRatingByRideId(Long rideId) {
        return ratingRepository.findByRideRideId(rideId)
                .orElseThrow(() -> new RuntimeException("Rating not found for ride id: " + rideId));
    }
    
    public Double getAverageRatingForDriver(Long driverId) {
        Double avg = ratingRepository.getAverageRatingForDriver(driverId);
        return avg != null ? avg : 0.0;
    }
    
    @Transactional
    public Rating updateRating(Long id, Rating rating) {
        Rating existingRating = getRatingById(id);
        
        existingRating.setRating(rating.getRating());
        existingRating.setReview(rating.getReview());
        
        Rating updatedRating = ratingRepository.save(existingRating);
        
        // Update driver's average rating
        driverService.updateDriverRating(existingRating.getDriver().getDriverId());
        
        return updatedRating;
    }
    
    @Transactional
    public void deleteRating(Long id) {
        Rating rating = getRatingById(id);
        Long driverId = rating.getDriver().getDriverId();
        
        ratingRepository.deleteById(id);
        
        // Update driver's average rating after deletion
        driverService.updateDriverRating(driverId);
    }
}
