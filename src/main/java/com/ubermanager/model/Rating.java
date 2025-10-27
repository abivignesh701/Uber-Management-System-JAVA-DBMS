package com.ubermanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "ratings")
public class Rating {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Long ratingId;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ride_id", nullable = false, unique = true)
    @NotNull(message = "Ride is required")
    private Ride ride;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message = "User is required")
    private User user;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "driver_id", nullable = false)
    @NotNull(message = "Driver is required")
    private Driver driver;
    
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    @Column(nullable = false)
    private Integer rating;
    
    @Column(columnDefinition = "TEXT")
    private String review;
    
    @Column(name = "rating_date")
    private LocalDateTime ratingDate;
    
    public Rating() {}
    
    public Rating(Long ratingId, Ride ride, User user, Driver driver, Integer rating, 
                  String review, LocalDateTime ratingDate) {
        this.ratingId = ratingId;
        this.ride = ride;
        this.user = user;
        this.driver = driver;
        this.rating = rating;
        this.review = review;
        this.ratingDate = ratingDate;
    }
    
    @PrePersist
    protected void onCreate() {
        if (ratingDate == null) {
            ratingDate = LocalDateTime.now();
        }
    }
    
    // Getters and Setters
    public Long getRatingId() { return ratingId; }
    public void setRatingId(Long ratingId) { this.ratingId = ratingId; }
    
    public Ride getRide() { return ride; }
    public void setRide(Ride ride) { this.ride = ride; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public Driver getDriver() { return driver; }
    public void setDriver(Driver driver) { this.driver = driver; }
    
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
    
    public String getReview() { return review; }
    public void setReview(String review) { this.review = review; }
    
    public LocalDateTime getRatingDate() { return ratingDate; }
    public void setRatingDate(LocalDateTime ratingDate) { this.ratingDate = ratingDate; }
}
