package com.ubermanager.dto;

/**
 * DTO for returning driver location information
 */
public class LocationResponseDTO {
    
    private Long driverId;
    private Double latitude;
    private Double longitude;
    private String driverName;
    private String vehicleNo;
    
    public LocationResponseDTO() {}
    
    public LocationResponseDTO(Long driverId, Double latitude, Double longitude) {
        this.driverId = driverId;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    public LocationResponseDTO(Long driverId, Double latitude, Double longitude, String driverName, String vehicleNo) {
        this.driverId = driverId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.driverName = driverName;
        this.vehicleNo = vehicleNo;
    }
    
    // Getters and Setters
    public Long getDriverId() {
        return driverId;
    }
    
    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }
    
    public Double getLatitude() {
        return latitude;
    }
    
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    
    public Double getLongitude() {
        return longitude;
    }
    
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    
    public String getDriverName() {
        return driverName;
    }
    
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
    
    public String getVehicleNo() {
        return vehicleNo;
    }
    
    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }
}
