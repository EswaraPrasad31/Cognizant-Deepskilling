package com.secure.platform.order.service.impl;
import com.secure.platform.order.client.*;
import com.secure.platform.order.dto.*;
import com.secure.platform.order.entity.Order;
import com.secure.platform.order.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceImplTest {
    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private UserClient userClient;
    @Mock
    private ProductClient productClient;
    @Mock
    private InventoryClient inventoryClient;
    @Mock
    private PaymentClient paymentClient;
    @Mock
    private BillingClient billingClient;
    @Mock
    private WebClient.Builder webClientBuilder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetOrderById() {
        UUID id = UUID.randomUUID();
        Order order = Order.builder().id(id).userId(UUID.randomUUID()).productId(UUID.randomUUID()).quantity(2).status("PLACED").build();
        when(orderRepository.findById(id)).thenReturn(Optional.of(order));
        OrderResponse response = orderService.getOrderById(id);
        assertNotNull(response);
        assertEquals("PLACED", response.getStatus());
    }
}
