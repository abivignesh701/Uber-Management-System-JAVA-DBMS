package com.ubermanager.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ubermanager.model.Payment;
import com.ubermanager.model.Ride;
import com.ubermanager.repository.PaymentRepository;

@Service
public class PaymentService {
    
    private final PaymentRepository paymentRepository;
    private final RideService rideService;
    
    @Autowired
    public PaymentService(PaymentRepository paymentRepository, RideService rideService) {
        this.paymentRepository = paymentRepository;
        this.rideService = rideService;
    }
    
    @Transactional
    public Payment createPayment(Payment payment) {
        Ride ride = rideService.getRideById(payment.getRide().getRideId());
        
        if (ride.getStatus() != Ride.RideStatus.COMPLETED) {
            throw new RuntimeException("Payment can only be made for completed rides");
        }
        
        if (paymentRepository.findByRideRideId(ride.getRideId()).isPresent()) {
            throw new RuntimeException("Payment already exists for this ride");
        }
        
        payment.setRide(ride);
        payment.setAmount(ride.getFare());
        payment.setPaymentDate(LocalDateTime.now());
        
        return paymentRepository.save(payment);
    }
    
    @Transactional
    public Payment processPayment(Long paymentId) {
        Payment payment = getPaymentById(paymentId);
        payment.setStatus(Payment.PaymentStatus.PAID);
        return paymentRepository.save(payment);
    }
    
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
    
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
    }
    
    public Payment getPaymentByRideId(Long rideId) {
        return paymentRepository.findByRideRideId(rideId)
                .orElseThrow(() -> new RuntimeException("Payment not found for ride id: " + rideId));
    }
    
    public Double getTotalPaidAmount() {
        Double total = paymentRepository.getTotalPaidAmount();
        return total != null ? total : 0.0;
    }
}
