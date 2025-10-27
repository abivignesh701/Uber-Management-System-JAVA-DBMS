package com.ubermanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ride_id", nullable = false, unique = true)
    @NotNull(message = "Ride is required")
    private Ride ride;
    
    @NotNull(message = "Amount is required")
    @Column(nullable = false)
    private Double amount;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;
    
    @Column(name = "payment_date")
    private LocalDateTime paymentDate;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status = PaymentStatus.PENDING;
    
    public Payment() {}
    
    public Payment(Long paymentId, Ride ride, Double amount, PaymentMethod paymentMethod, 
                   LocalDateTime paymentDate, PaymentStatus status) {
        this.paymentId = paymentId;
        this.ride = ride;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.status = status;
    }
    
    @PrePersist
    protected void onCreate() {
        if (paymentDate == null) {
            paymentDate = LocalDateTime.now();
        }
        if (status == null) {
            status = PaymentStatus.PENDING;
        }
    }
    
    // Getters and Setters
    public Long getPaymentId() { return paymentId; }
    public void setPaymentId(Long paymentId) { this.paymentId = paymentId; }
    
    public Ride getRide() { return ride; }
    public void setRide(Ride ride) { this.ride = ride; }
    
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    
    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }
    
    public LocalDateTime getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }
    
    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }
    
    public enum PaymentMethod {
        CASH,
        CARD,
        WALLET
    }
    
    public enum PaymentStatus {
        PAID,
        PENDING,
        FAILED
    }
}
