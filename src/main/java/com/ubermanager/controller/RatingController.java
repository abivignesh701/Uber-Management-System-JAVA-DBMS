package com.ubermanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubermanager.model.Rating;
import com.ubermanager.service.RatingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ratings")
@CrossOrigin(origins = "*")
public class RatingController {
    
    private final RatingService ratingService;
    
    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }
    
    @PostMapping
    public ResponseEntity<Rating> createRating(@Valid @RequestBody Rating rating) {
        Rating createdRating = ratingService.createRating(rating);
        return new ResponseEntity<>(createdRating, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings() {
        List<Rating> ratings = ratingService.getAllRatings();
        return ResponseEntity.ok(ratings);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Rating> getRatingById(@PathVariable Long id) {
        Rating rating = ratingService.getRatingById(id);
        return ResponseEntity.ok(rating);
    }
    
    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<Rating>> getRatingsByDriver(@PathVariable Long driverId) {
        List<Rating> ratings = ratingService.getRatingsByDriver(driverId);
        return ResponseEntity.ok(ratings);
    }
    
    @GetMapping("/driver/{driverId}/average")
    public ResponseEntity<Double> getAverageRatingForDriver(@PathVariable Long driverId) {
        Double avgRating = ratingService.getAverageRatingForDriver(driverId);
        return ResponseEntity.ok(avgRating);
    }
}
