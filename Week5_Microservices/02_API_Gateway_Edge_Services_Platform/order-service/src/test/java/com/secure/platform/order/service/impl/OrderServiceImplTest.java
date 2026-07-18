package com.secure.platform.order.service.impl;

import com.secure.platform.order.client.ProductClient;
import com.secure.platform.order.client.UserClient;
import com.secure.platform.order.dto.OrderRequest;
import com.secure.platform.order.dto.OrderResponse;
import com.secure.platform.order.dto.ProductResponse;
import com.secure.platform.order.dto.UserResponse;
import com.secure.platform.order.entity.Order;
import com.secure.platform.order.mapper.OrderMapper;
import com.secure.platform.order.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserClient userClient;

    @Mock
    private ProductClient productClient;

    private final OrderMapper orderMapper = new OrderMapper();

    private OrderServiceImpl orderService;

    private UUID userId;
    private UUID productId;
    private UUID orderId;
    private Order order;
    private UserResponse userResponse;
    private ProductResponse productResponse;

    @BeforeEach
    void setUp() {
        orderService = new OrderServiceImpl(orderRepository, orderMapper, userClient, productClient);
        userId = UUID.randomUUID();
        productId = UUID.randomUUID();
        orderId = UUID.randomUUID();

        order = Order.builder()
                .id(orderId)
                .userId(userId)
                .productId(productId)
                .quantity(2)
                .status("PLACED")
                .build();

        userResponse = UserResponse.builder()
                .id(userId)
                .name("John Doe")
                .email("john@example.com")
                .role("CUSTOMER")
                .build();

        productResponse = ProductResponse.builder()
                .id(productId)
                .name("Test Product")
                .price(new BigDecimal("100.00"))
                .quantity(10)
                .build();
    }

    @Test
    void placeOrder_Success() {
        OrderRequest request = OrderRequest.builder()
                .productId(productId)
                .quantity(2)
                .build();

        when(userClient.getUserById(userId)).thenReturn(userResponse);
        when(productClient.getProductById(productId)).thenReturn(productResponse);
        doNothing().when(productClient).reduceStock(productId, 2);
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        OrderResponse response = orderService.placeOrder(userId, request);

        assertNotNull(response);
        assertEquals("PLACED", response.getStatus());
        assertEquals(2, response.getQuantity());
        verify(productClient, times(1)).reduceStock(productId, 2);
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void placeOrder_ThrowsException_WhenInsufficientStock() {
        OrderRequest request = OrderRequest.builder()
                .productId(productId)
                .quantity(15)
                .build();

        when(userClient.getUserById(userId)).thenReturn(userResponse);
        when(productClient.getProductById(productId)).thenReturn(productResponse);

        assertThrows(IllegalArgumentException.class, () -> orderService.placeOrder(userId, request));
        verify(productClient, never()).reduceStock(any(UUID.class), anyInt());
        verify(orderRepository, never()).save(any(Order.class));
    }

    @Test
    void cancelOrder_Success_ByOwner() {
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        OrderResponse response = orderService.cancelOrder(orderId, userId, "ROLE_CUSTOMER");

        assertNotNull(response);
        assertEquals("CANCELLED", order.getStatus());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void cancelOrder_Success_ByAdmin() {
        UUID adminId = UUID.randomUUID();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        OrderResponse response = orderService.cancelOrder(orderId, adminId, "ROLE_ADMIN");

        assertNotNull(response);
        assertEquals("CANCELLED", order.getStatus());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void cancelOrder_ThrowsException_ByUnauthorizedUser() {
        UUID unauthorizedUserId = UUID.randomUUID();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        assertThrows(IllegalArgumentException.class, () -> orderService.cancelOrder(orderId, unauthorizedUserId, "ROLE_CUSTOMER"));
        verify(orderRepository, never()).save(any(Order.class));
    }
}
