package com.ubermanager.service;

import com.ubermanager.dto.LocationRequestDTO;
import com.ubermanager.dto.LocationResponseDTO;
import com.ubermanager.model.Driver;
import com.ubermanager.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for managing driver GPS location tracking
 * Handles location updates and retrieval with validation
 * 
 * Future enhancements:
 * - Add WebSocket support for real-time location broadcasting
 * - Integrate with STOMP for live updates to connected clients
 * - Add location history tracking for analytics
 * - Implement geofencing and proximity alerts
 */
@Service
public class LocationService {
    
    @Autowired
    private DriverRepository driverRepository;
    
    /**
     * Update driver's current GPS location
     * 
     * @param driverId ID of the driver
     * @param locationRequest DTO containing latitude and longitude
     * @return success message
     * @throws RuntimeException if driver not found
     */
    @Transactional
    public String updateDriverLocation(Long driverId, LocationRequestDTO locationRequest) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found with ID: " + driverId));
        
        // Validate coordinates (already validated by DTO annotations, but double-check)
        validateCoordinates(locationRequest.getLatitude(), locationRequest.getLongitude());
        
        // Update driver location
        driver.setCurrentLatitude(locationRequest.getLatitude());
        driver.setCurrentLongitude(locationRequest.getLongitude());
        
        driverRepository.save(driver);
        
        // TODO: Future enhancement - broadcast location update via WebSocket
        // Example: messagingTemplate.convertAndSend("/topic/driver/" + driverId, locationResponse);
        
        return "Driver location updated successfully";
    }
    
    /**
     * Get driver's current GPS location
     * 
     * @param driverId ID of the driver
     * @return LocationResponseDTO with current coordinates
     * @throws RuntimeException if driver not found
     */
    public LocationResponseDTO getDriverLocation(Long driverId) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found with ID: " + driverId));
        
        return new LocationResponseDTO(
                driver.getDriverId(),
                driver.getCurrentLatitude(),
                driver.getCurrentLongitude(),
                driver.getName(),
                driver.getVehicleNo()
        );
    }
    
    /**
     * Validate GPS coordinates
     * 
     * @param latitude must be between -90 and 90
     * @param longitude must be between -180 and 180
     * @throws IllegalArgumentException if coordinates are invalid
     */
    private void validateCoordinates(Double latitude, Double longitude) {
        if (latitude == null || longitude == null) {
            throw new IllegalArgumentException("Latitude and longitude cannot be null");
        }
        
        if (latitude < -90.0 || latitude > 90.0) {
            throw new IllegalArgumentException("Latitude must be between -90 and 90, got: " + latitude);
        }
        
        if (longitude < -180.0 || longitude > 180.0) {
            throw new IllegalArgumentException("Longitude must be between -180 and 180, got: " + longitude);
        }
    }
    
    /**
     * TODO: Future enhancement - Calculate distance between two GPS coordinates
     * Useful for finding nearby drivers or calculating ride distance
     * 
     * @param lat1 latitude of first point
     * @param lon1 longitude of first point
     * @param lat2 latitude of second point
     * @param lon2 longitude of second point
     * @return distance in kilometers using Haversine formula
     */
    @SuppressWarnings("unused")
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of the Earth in kilometers
        
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        
        return R * c; // Distance in kilometers
    }
}
