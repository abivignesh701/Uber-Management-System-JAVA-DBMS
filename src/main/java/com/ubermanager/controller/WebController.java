package com.ubermanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ubermanager.service.DriverService;
import com.ubermanager.service.PaymentService;
import com.ubermanager.service.RatingService;
import com.ubermanager.service.RideService;
import com.ubermanager.service.UserService;

@Controller
public class WebController {
    
    private final UserService userService;
    private final DriverService driverService;
    private final RideService rideService;
    private final PaymentService paymentService;
    private final RatingService ratingService;
    
    @Autowired
    public WebController(UserService userService, DriverService driverService, RideService rideService, 
                        PaymentService paymentService, RatingService ratingService) {
        this.userService = userService;
        this.driverService = driverService;
        this.rideService = rideService;
        this.paymentService = paymentService;
        this.ratingService = ratingService;
    }
    
    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }
    
    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }
    
    @GetMapping("/drivers")
    public String drivers(Model model) {
        model.addAttribute("drivers", driverService.getAllDrivers());
        return "drivers";
    }
    
    @GetMapping("/rides")
    public String rides(Model model) {
        model.addAttribute("rides", rideService.getAllRides());
        return "rides";
    }
    
    @GetMapping("/payments")
    public String payments(Model model) {
        model.addAttribute("payments", paymentService.getAllPayments());
        return "payments";
    }
    
    @GetMapping("/ratings")
    public String ratings(Model model) {
        model.addAttribute("ratings", ratingService.getAllRatings());
        return "ratings";
    }
    
    @GetMapping("/user/dashboard")
    public String userDashboard() {
        return "user-dashboard";
    }
    
    @GetMapping("/driver/dashboard")
    public String driverDashboard() {
        return "driver-dashboard";
    }
    
    @GetMapping("/dashboard")
    public String mainDashboard(Model model) {
        model.addAttribute("totalUsers", userService.getTotalUsersCount());
        model.addAttribute("totalDrivers", driverService.getTotalDriversCount());
        model.addAttribute("totalRides", rideService.getTotalRidesCount());
        model.addAttribute("completedRides", rideService.getCompletedRidesCount());
        model.addAttribute("totalRevenue", rideService.getTotalRevenue());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("drivers", driverService.getAllDrivers());
        model.addAttribute("recentRides", rideService.getAllRides());
        return "dashboard";
    }
}
