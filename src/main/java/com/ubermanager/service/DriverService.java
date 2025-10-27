package com.ubermanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ubermanager.model.Driver;
import com.ubermanager.repository.DriverRepository;
import com.ubermanager.repository.RatingRepository;

@Service
public class DriverService {
    
    private final DriverRepository driverRepository;
    private final RatingRepository ratingRepository;
    
    @Autowired
    public DriverService(DriverRepository driverRepository, RatingRepository ratingRepository) {
        this.driverRepository = driverRepository;
        this.ratingRepository = ratingRepository;
    }
    
    @Transactional
    public Driver createDriver(Driver driver) {
        if (driverRepository.existsByEmail(driver.getEmail())) {
            throw new RuntimeException("Driver with email " + driver.getEmail() + " already exists");
        }
        return driverRepository.save(driver);
    }
    
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }
    
    public Driver getDriverById(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found with id: " + id));
    }
    
    public Driver getDriverByEmail(String email) {
        return driverRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Driver not found with email: " + email));
    }
    
    @Transactional
    public Driver updateDriver(Long id, Driver driverDetails) {
        Driver driver = getDriverById(id);
        driver.setName(driverDetails.getName());
        driver.setContactNo(driverDetails.getContactNo());
        driver.setVehicleNo(driverDetails.getVehicleNo());
        return driverRepository.save(driver);
    }
    
    @Transactional
    public void deleteDriver(Long id) {
        Driver driver = getDriverById(id);
        driverRepository.delete(driver);
    }
    
    @Transactional
    public void updateDriverRating(Long driverId) {
        Driver driver = getDriverById(driverId);
        Double avgRating = ratingRepository.getAverageRatingForDriver(driverId);
        driver.setRatingAvg(avgRating != null ? avgRating : 0.0);
        driverRepository.save(driver);
    }
    
    public List<Driver> getTopRatedDrivers() {
        return driverRepository.findTopRatedDrivers();
    }
    
    public Long getTotalDriversCount() {
        return driverRepository.count();
    }
}
