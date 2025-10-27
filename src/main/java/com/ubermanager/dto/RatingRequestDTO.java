package com.ubermanager.dto;

public class RatingRequestDTO {
    private Long rideId;
    private Integer rating;
    private String review;
    
    public RatingRequestDTO() {}
    
    public RatingRequestDTO(Long rideId, Integer rating, String review) {
        this.rideId = rideId;
        this.rating = rating;
        this.review = review;
    }
    
    public Long getRideId() {
        return rideId;
    }
    
    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }
    
    public Integer getRating() {
        return rating;
    }
    
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    
    public String getReview() {
        return review;
    }
    
    public void setReview(String review) {
        this.review = review;
    }
}
