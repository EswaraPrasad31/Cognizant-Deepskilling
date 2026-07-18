package com.secure.platform.payment.service;
import com.secure.platform.payment.dto.*;
import java.util.UUID;

public interface PaymentService {
    PaymentResponse processPayment(PaymentRequest request);
    PaymentResponse getPaymentById(UUID id);
}
