package com.ubermanager.dto;

import com.ubermanager.model.Payment;

public class PaymentRequestDTO {
    private Long rideId;
    private Payment.PaymentMethod paymentMethod;
    
    public PaymentRequestDTO() {}
    
    public PaymentRequestDTO(Long rideId, Payment.PaymentMethod paymentMethod) {
        this.rideId = rideId;
        this.paymentMethod = paymentMethod;
    }
    
    public Long getRideId() {
        return rideId;
    }
    
    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }
    
    public Payment.PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(Payment.PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
