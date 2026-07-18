package com.secure.platform.order.service;

import com.secure.platform.order.dto.OrderRequest;
import com.secure.platform.order.dto.OrderResponse;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderResponse placeOrder(UUID userId, OrderRequest request);
    OrderResponse cancelOrder(UUID id, UUID userId, String role);
    List<OrderResponse> getOrdersByUserId(UUID userId);
    List<OrderResponse> getAllOrders();
    OrderResponse getOrderById(UUID id, UUID userId, String role);
}
