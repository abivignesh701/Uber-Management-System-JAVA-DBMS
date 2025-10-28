package com.ubermanager.controller;

import com.ubermanager.model.Driver;
import com.ubermanager.model.User;
import com.ubermanager.repository.DriverRepository;
import com.ubermanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import java.time.LocalDate;

@Controller
public class AuthController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private DriverRepository driverRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping("/login")
    public String showLoginPage(Model model) {
        return "login";
    }
    
    @GetMapping("/register/user")
    public String showUserRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "register-user";
    }
    
    @GetMapping("/register/driver")
    public String showDriverRegistrationPage(Model model) {
        model.addAttribute("driver", new Driver());
        return "register-driver";
    }
    
    @PostMapping("/register/user")
    public String registerUser(@Valid @ModelAttribute("user") User user, 
                              BindingResult result, 
                              RedirectAttributes redirectAttributes,
                              Model model) {
        
        // Check if email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("error", "Email already registered!");
            return "register-user";
        }
        
        if (result.hasErrors()) {
            return "register-user";
        }
        
        // Encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        user.setJoinDate(LocalDate.now());
        
        userRepository.save(user);
        
        redirectAttributes.addFlashAttribute("success", "Registration successful! Please login.");
        return "redirect:/login";
    }
    
    @PostMapping("/register/driver")
    public String registerDriver(@Valid @ModelAttribute("driver") Driver driver, 
                                 BindingResult result, 
                                 RedirectAttributes redirectAttributes,
                                 Model model) {
        
        // Check if email already exists
        if (driverRepository.findByEmail(driver.getEmail()).isPresent()) {
            model.addAttribute("error", "Email already registered!");
            return "register-driver";
        }
        
        if (result.hasErrors()) {
            return "register-driver";
        }
        
        // Encrypt password
        driver.setPassword(passwordEncoder.encode(driver.getPassword()));
        driver.setRole("ROLE_DRIVER");
        driver.setJoinDate(LocalDate.now());
        driver.setRatingAvg(0.0);
        
        driverRepository.save(driver);
        
        redirectAttributes.addFlashAttribute("success", "Registration successful! Please login.");
        return "redirect:/login";
    }
}
