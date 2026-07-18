package com.secure.platform.order.service;
import com.secure.platform.order.dto.*;
import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest request);
    void cancelOrder(UUID orderId);
    List<OrderResponse> getAllOrders();
    OrderResponse getOrderById(UUID id);
}
