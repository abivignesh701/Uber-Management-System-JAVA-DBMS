package com.ubermanager.controller;

import com.ubermanager.dto.LocationRequestDTO;
import com.ubermanager.dto.LocationResponseDTO;
import com.ubermanager.service.LocationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * REST Controller for driver GPS location tracking
 * 
 * Endpoints:
 * - PUT  /api/drivers/{id}/location - Update driver location
 * - GET  /api/drivers/{id}/location - Get driver current location
 * 
 * Future enhancements:
 * - Add WebSocket endpoint for real-time location streaming
 * - Integrate with Google Maps API for frontend visualization
 * - Add endpoint to get all active drivers within a radius
 * - Implement location history endpoint for analytics
 */
@RestController
@RequestMapping("/api/drivers")
@CrossOrigin(origins = "*") // Allow CORS for frontend integration
public class LocationController {
    
    @Autowired
    private LocationService locationService;
    
    /**
     * Update driver's current GPS location
     * 
     * @param id driver ID from path variable
     * @param locationRequest DTO with latitude and longitude
     * @return success message
     * 
     * Example request:
     * PUT /api/drivers/5/location
     * {
     *   "latitude": 12.9716,
     *   "longitude": 77.5946
     * }
     * 
     * Example response:
     * {
     *   "message": "Driver location updated successfully"
     * }
     */
    @PutMapping("/{id}/location")
    public ResponseEntity<Map<String, String>> updateDriverLocation(
            @PathVariable("id") Long id,
            @Valid @RequestBody LocationRequestDTO locationRequest) {
        
        try {
            String message = locationService.updateDriverLocation(id, locationRequest);
            
            Map<String, String> response = new HashMap<>();
            response.put("message", message);
            
            return ResponseEntity.ok(response);
            
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            
            if (e.getMessage().contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
    
    /**
     * Get driver's current GPS location
     * 
     * @param id driver ID from path variable
     * @return LocationResponseDTO with current coordinates
     * 
     * Example request:
     * GET /api/drivers/5/location
     * 
     * Example response:
     * {
     *   "driverId": 5,
     *   "latitude": 12.9716,
     *   "longitude": 77.5946,
     *   "driverName": "Sanjay Patel",
     *   "vehicleNo": "DL01AB1238"
     * }
     */
    @GetMapping("/{id}/location")
    public ResponseEntity<?> getDriverLocation(@PathVariable("id") Long id) {
        
        try {
            LocationResponseDTO location = locationService.getDriverLocation(id);
            return ResponseEntity.ok(location);
            
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            
            if (e.getMessage().contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    
    /**
     * TODO: Future WebSocket endpoint for real-time location streaming
     * 
     * @MessageMapping("/driver/{id}/location")
     * @SendTo("/topic/driver/{id}")
     * public LocationResponseDTO broadcastLocation(@DestinationVariable Long id, LocationRequestDTO location) {
     *     locationService.updateDriverLocation(id, location);
     *     return locationService.getDriverLocation(id);
     * }
     */
    
    /**
     * TODO: Future endpoint to get nearby drivers
     * GET /api/drivers/nearby?latitude=12.9716&longitude=77.5946&radius=5
     * 
     * @GetMapping("/nearby")
     * public ResponseEntity<List<LocationResponseDTO>> getNearbyDrivers(
     *         @RequestParam Double latitude,
     *         @RequestParam Double longitude,
     *         @RequestParam(defaultValue = "5") Double radius) {
     *     // Implementation would use the calculateDistance method in LocationService
     *     // to find drivers within the specified radius (in kilometers)
     * }
     */
}
