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
import com.secure.platform.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserClient userClient;
    private final ProductClient productClient;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, 
                            UserClient userClient, ProductClient productClient) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.userClient = userClient;
        this.productClient = productClient;
    }

    @Override
    public OrderResponse placeOrder(UUID userId, OrderRequest request) {
        log.info("Placing order for user ID: {} and product ID: {}", userId, request.getProductId());

        // 1. Verify User exists
        try {
            UserResponse user = userClient.getUserById(userId);
            log.info("User verified: {}", user.getEmail());
        } catch (Exception e) {
            log.error("User validation failed for ID: {}. Error: {}", userId, e.getMessage());
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }

        // 2. Verify Product exists and has sufficient stock
        ProductResponse product;
        try {
            product = productClient.getProductById(request.getProductId());
            log.info("Product verified: {}. Available stock: {}", product.getName(), product.getQuantity());
        } catch (Exception e) {
            log.error("Product validation failed for ID: {}. Error: {}", request.getProductId(), e.getMessage());
            throw new IllegalArgumentException("Product not found with ID: " + request.getProductId());
        }

        if (product.getQuantity() < request.getQuantity()) {
            log.warn("Insufficient stock for product: {}. Requested: {}, Available: {}", 
                    product.getName(), request.getQuantity(), product.getQuantity());
            throw new IllegalArgumentException("Insufficient stock for product: " + product.getName());
        }

        // 3. Deduct stock
        try {
            productClient.reduceStock(request.getProductId(), request.getQuantity());
            log.info("Stock reduced successfully in product service");
        } catch (Exception e) {
            log.error("Failed to reduce stock for product ID: {}. Error: {}", request.getProductId(), e.getMessage());
            throw new IllegalArgumentException("Could not deduct stock: " + e.getMessage());
        }

        // 4. Save order
        Order order = orderMapper.toEntity(request);
        order.setUserId(userId);
        order.setStatus("PLACED");

        Order savedOrder = orderRepository.save(order);
        log.info("Order placed successfully with ID: {}", savedOrder.getId());
        return orderMapper.toDto(savedOrder);
    }

    @Override
    public OrderResponse cancelOrder(UUID id, UUID userId, String role) {
        log.info("Cancelling order ID: {} requested by user: {} with role: {}", id, userId, role);
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + id));

        // Security check: Only the user who placed it or an ADMIN can cancel it
        boolean isAdmin = "ROLE_ADMIN".equals(role) || "ADMIN".equals(role);
        if (!order.getUserId().equals(userId) && !isAdmin) {
            log.warn("Unauthorized attempt to cancel order ID: {} by user: {}", id, userId);
            throw new IllegalArgumentException("You are not authorized to cancel this order");
        }

        if ("CANCELLED".equals(order.getStatus())) {
            log.warn("Order ID: {} is already cancelled", id);
            throw new IllegalArgumentException("Order is already cancelled");
        }

        order.setStatus("CANCELLED");
        Order updatedOrder = orderRepository.save(order);
        log.info("Order ID: {} cancelled successfully", id);
        return orderMapper.toDto(updatedOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getOrdersByUserId(UUID userId) {
        log.info("Fetching order history for user ID: {}", userId);
        return orderRepository.findByUserId(userId).stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getAllOrders() {
        log.info("Fetching all orders in the system");
        return orderRepository.findAll().stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponse getOrderById(UUID id, UUID userId, String role) {
        log.info("Fetching order ID: {} requested by user: {} with role: {}", id, userId, role);
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + id));

        // Security check: Only owner, ADMIN, or MANAGER can view
        boolean isAuthorized = order.getUserId().equals(userId) 
                || "ROLE_ADMIN".equals(role) || "ADMIN".equals(role)
                || "ROLE_MANAGER".equals(role) || "MANAGER".equals(role);
                
        if (!isAuthorized) {
            log.warn("Unauthorized attempt to view order ID: {} by user: {}", id, userId);
            throw new IllegalArgumentException("You are not authorized to view this order");
        }

        return orderMapper.toDto(order);
    }
}
