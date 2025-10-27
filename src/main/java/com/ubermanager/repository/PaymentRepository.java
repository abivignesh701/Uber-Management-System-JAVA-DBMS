package com.ubermanager.repository;

import com.ubermanager.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByRideRideId(Long rideId);
    
    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.status = 'PAID'")
    Double getTotalPaidAmount();
}
