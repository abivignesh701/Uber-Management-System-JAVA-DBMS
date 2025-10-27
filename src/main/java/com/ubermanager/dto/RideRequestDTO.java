package com.ubermanager.dto;

public class RideRequestDTO {
    private Long userId;
    private String pickupLocation;
    private String dropLocation;
    
    public RideRequestDTO() {}
    
    public RideRequestDTO(Long userId, String pickupLocation, String dropLocation) {
        this.userId = userId;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getPickupLocation() {
        return pickupLocation;
    }
    
    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }
    
    public String getDropLocation() {
        return dropLocation;
    }
    
    public void setDropLocation(String dropLocation) {
        this.dropLocation = dropLocation;
    }
}
