package com.ubermanager.controller;

import com.ubermanager.model.Ride;
import com.ubermanager.service.RideService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rides")
@CrossOrigin(origins = "*")
public class RideController {
    
    private final RideService rideService;
    
    @Autowired
    public RideController(RideService rideService) {
        this.rideService = rideService;
    }
    
    @PostMapping
    public ResponseEntity<Ride> requestRide(@Valid @RequestBody Ride ride) {
        Ride requestedRide = rideService.requestRide(ride);
        return new ResponseEntity<>(requestedRide, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<Ride>> getAllRides() {
        List<Ride> rides = rideService.getAllRides();
        return ResponseEntity.ok(rides);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Ride> getRideById(@PathVariable Long id) {
        Ride ride = rideService.getRideById(id);
        return ResponseEntity.ok(ride);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Ride>> getRidesByUser(@PathVariable Long userId) {
        List<Ride> rides = rideService.getRidesByUser(userId);
        return ResponseEntity.ok(rides);
    }
    
    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<Ride>> getRidesByDriver(@PathVariable Long driverId) {
        List<Ride> rides = rideService.getRidesByDriver(driverId);
        return ResponseEntity.ok(rides);
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Ride>> getRidesByStatus(@PathVariable Ride.RideStatus status) {
        List<Ride> rides = rideService.getRidesByStatus(status);
        return ResponseEntity.ok(rides);
    }
    
    @PutMapping("/{rideId}/accept/{driverId}")
    public ResponseEntity<Ride> acceptRide(@PathVariable Long rideId, @PathVariable Long driverId) {
        Ride ride = rideService.acceptRide(rideId, driverId);
        return ResponseEntity.ok(ride);
    }
    
    @PutMapping("/{rideId}/start")
    public ResponseEntity<Ride> startRide(@PathVariable Long rideId) {
        Ride ride = rideService.startRide(rideId);
        return ResponseEntity.ok(ride);
    }
    
    @PutMapping("/{rideId}/complete")
    public ResponseEntity<Ride> completeRide(@PathVariable Long rideId) {
        Ride ride = rideService.completeRide(rideId);
        return ResponseEntity.ok(ride);
    }
    
    @PutMapping("/{rideId}/cancel")
    public ResponseEntity<Ride> cancelRide(@PathVariable Long rideId) {
        Ride ride = rideService.cancelRide(rideId);
        return ResponseEntity.ok(ride);
    }
}
