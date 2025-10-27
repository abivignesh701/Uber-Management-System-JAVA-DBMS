package com.ubermanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "rides")
public class Ride {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ride_id")
    private Long rideId;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message = "User is required")
    private User user;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "driver_id")
    private Driver driver;
    
    @NotBlank(message = "Pickup location is required")
    @Column(name = "pickup_location", nullable = false)
    private String pickupLocation;
    
    @NotBlank(message = "Drop location is required")
    @Column(name = "drop_location", nullable = false)
    private String dropLocation;
    
    @Column(name = "request_time")
    private LocalDateTime requestTime;
    
    @Column(name = "start_time")
    private LocalDateTime startTime;
    
    @Column(name = "end_time")
    private LocalDateTime endTime;
    
    @Column(name = "fare")
    private Double fare;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RideStatus status = RideStatus.REQUESTED;
    
    @OneToOne(mappedBy = "ride", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Payment payment;
    
    @OneToOne(mappedBy = "ride", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Rating rating;
    
    public Ride() {}
    
    public Ride(Long rideId, User user, Driver driver, String pickupLocation, String dropLocation, 
                LocalDateTime requestTime, LocalDateTime startTime, LocalDateTime endTime, Double fare, 
                RideStatus status, Payment payment, Rating rating) {
        this.rideId = rideId;
        this.user = user;
        this.driver = driver;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.requestTime = requestTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.fare = fare;
        this.status = status;
        this.payment = payment;
        this.rating = rating;
    }
    
    @PrePersist
    protected void onCreate() {
        if (requestTime == null) {
            requestTime = LocalDateTime.now();
        }
        if (status == null) {
            status = RideStatus.REQUESTED;
        }
    }
    
    // Getters and Setters
    public Long getRideId() { return rideId; }
    public void setRideId(Long rideId) { this.rideId = rideId; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public Driver getDriver() { return driver; }
    public void setDriver(Driver driver) { this.driver = driver; }
    
    public String getPickupLocation() { return pickupLocation; }
    public void setPickupLocation(String pickupLocation) { this.pickupLocation = pickupLocation; }
    
    public String getDropLocation() { return dropLocation; }
    public void setDropLocation(String dropLocation) { this.dropLocation = dropLocation; }
    
    public LocalDateTime getRequestTime() { return requestTime; }
    public void setRequestTime(LocalDateTime requestTime) { this.requestTime = requestTime; }
    
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    
    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
    
    public Double getFare() { return fare; }
    public void setFare(Double fare) { this.fare = fare; }
    
    public RideStatus getStatus() { return status; }
    public void setStatus(RideStatus status) { this.status = status; }
    
    public Payment getPayment() { return payment; }
    public void setPayment(Payment payment) { this.payment = payment; }
    
    public Rating getRating() { return rating; }
    public void setRating(Rating rating) { this.rating = rating; }
    
    public enum RideStatus {
        REQUESTED,
        ACCEPTED,
        IN_PROGRESS,
        COMPLETED,
        CANCELLED
    }
}
