package com.secure.platform.payment.service.impl;
import com.secure.platform.payment.dto.*;
import com.secure.platform.payment.entity.Payment;
import com.secure.platform.payment.repository.PaymentRepository;
import com.secure.platform.payment.service.PaymentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Override
    @CircuitBreaker(name = "thirdPartyPayment", fallbackMethod = "paymentFallback")
    public PaymentResponse processPayment(PaymentRequest request) {
        log.info("Initiating payment processing for order: {}", request.getOrderId());
        
        // Simulating delay for third-party call, if amount > 500 we trigger fallback
        if (request.getAmount().doubleValue() > 500.0) {
            log.warn("Amount exceeds limit. Simulating slow payment gateway delay...");
            try {
                Thread.sleep(3000); // 3s delay (exceeds 2s limit)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            throw new RuntimeException("Slow network gateway exception");
        }
        
        Payment payment = Payment.builder()
                .orderId(request.getOrderId())
                .amount(request.getAmount())
                .transactionId("TXN-" + System.currentTimeMillis())
                .status("SUCCESS")
                .build();
        payment = paymentRepository.save(payment);
        return PaymentResponse.builder()
                .id(payment.getId())
                .orderId(payment.getOrderId())
                .amount(payment.getAmount())
                .transactionId(payment.getTransactionId())
                .status(payment.getStatus())
                .message("Payment processed successfully")
                .build();
    }

    public PaymentResponse paymentFallback(PaymentRequest request, Throwable ex) {
        log.error("Payment fallback triggered. Error: {}", ex.getMessage());
        return PaymentResponse.builder()
                .orderId(request.getOrderId())
                .amount(request.getAmount())
                .status("FAILED")
                .message("Payment service temporarily unavailable.")
                .build();
    }

    @Override
    public PaymentResponse getPaymentById(UUID id) {
        log.info("Getting payment details with ID: {}", id);
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));
        return PaymentResponse.builder()
                .id(payment.getId())
                .orderId(payment.getOrderId())
                .amount(payment.getAmount())
                .transactionId(payment.getTransactionId())
                .status(payment.getStatus())
                .build();
    }
}
