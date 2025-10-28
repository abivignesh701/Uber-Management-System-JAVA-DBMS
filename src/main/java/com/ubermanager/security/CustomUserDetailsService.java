package com.ubermanager.security;

import com.ubermanager.model.Driver;
import com.ubermanager.model.User;
import com.ubermanager.repository.DriverRepository;
import com.ubermanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private DriverRepository driverRepository;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // First, try to find user in users table
        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null) {
            return new CustomUserDetails(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole(),
                "USER"
            );
        }
        
        // If not found, try to find in drivers table
        Driver driver = driverRepository.findByEmail(email).orElse(null);
        if (driver != null) {
            return new CustomUserDetails(
                driver.getDriverId(),
                driver.getName(),
                driver.getEmail(),
                driver.getPassword(),
                driver.getRole(),
                "DRIVER"
            );
        }
        
        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
