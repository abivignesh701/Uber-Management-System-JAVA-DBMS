package com.ubermanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    
    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;
    
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    @Column(unique = true, nullable = false)
    private String email;
    
    @NotBlank(message = "Contact number is required")
    @Column(name = "contact_no", nullable = false)
    private String contactNo;
    
    @Column(name = "join_date")
    private LocalDate joinDate;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ride> rides;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Rating> ratings;
    
    public User() {}
    
    public User(Long userId, String name, String email, String contactNo, LocalDate joinDate, List<Ride> rides, List<Rating> ratings) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
        this.joinDate = joinDate;
        this.rides = rides;
        this.ratings = ratings;
    }
    
    @PrePersist
    protected void onCreate() {
        if (joinDate == null) {
            joinDate = LocalDate.now();
        }
    }
    
    // Getters and Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getContactNo() { return contactNo; }
    public void setContactNo(String contactNo) { this.contactNo = contactNo; }
    
    public LocalDate getJoinDate() { return joinDate; }
    public void setJoinDate(LocalDate joinDate) { this.joinDate = joinDate; }
    
    public List<Ride> getRides() { return rides; }
    public void setRides(List<Ride> rides) { this.rides = rides; }
    
    public List<Rating> getRatings() { return ratings; }
    public void setRatings(List<Rating> ratings) { this.ratings = ratings; }
}
