package com.secure.platform.payment.service.impl;
import com.secure.platform.payment.dto.*;
import com.secure.platform.payment.entity.Payment;
import com.secure.platform.payment.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PaymentServiceImplTest {
    @InjectMocks
    private PaymentServiceImpl paymentService;
    @Mock
    private PaymentRepository paymentRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPaymentById() {
        UUID id = UUID.randomUUID();
        Payment payment = Payment.builder().id(id).orderId(UUID.randomUUID()).amount(BigDecimal.valueOf(100)).status("SUCCESS").build();
        when(paymentRepository.findById(id)).thenReturn(Optional.of(payment));
        PaymentResponse response = paymentService.getPaymentById(id);
        assertNotNull(response);
        assertEquals("SUCCESS", response.getStatus());
    }
}
