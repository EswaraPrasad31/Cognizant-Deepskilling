package com.secure.platform.order.controller;

import com.secure.platform.order.dto.OrderRequest;
import com.secure.platform.order.dto.OrderResponse;
import com.secure.platform.order.service.OrderService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<OrderResponse> placeOrder(
            @AuthenticationPrincipal Jwt jwt, 
            @Valid @RequestBody OrderRequest request) {
        UUID userId = UUID.fromString(jwt.getClaimAsString("userId"));
        log.info("REST request to place order for product ID: {} by user ID: {}", request.getProductId(), userId);
        OrderResponse response = orderService.placeOrder(userId, request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/cancel")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public ResponseEntity<OrderResponse> cancelOrder(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID id) {
        UUID userId = UUID.fromString(jwt.getClaimAsString("userId"));
        String role = getHighestRole(jwt);
        log.info("REST request to cancel order ID: {} by user ID: {} with role: {}", id, userId, role);
        OrderResponse response = orderService.cancelOrder(id, userId, role);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/my-orders")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<List<OrderResponse>> getMyOrders(@AuthenticationPrincipal Jwt jwt) {
        UUID userId = UUID.fromString(jwt.getClaimAsString("userId"));
        log.info("REST request to fetch order history for customer ID: {}", userId);
        List<OrderResponse> response = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        log.info("REST request by ADMIN/MANAGER to fetch all orders");
        List<OrderResponse> response = orderService.getAllOrders();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID id) {
        UUID userId = UUID.fromString(jwt.getClaimAsString("userId"));
        String role = getHighestRole(jwt);
        log.info("REST request to fetch order ID: {} by user ID: {} with role: {}", id, userId, role);
        OrderResponse response = orderService.getOrderById(id, userId, role);
        return ResponseEntity.ok(response);
    }

    private String getHighestRole(Jwt jwt) {
        List<String> roles = jwt.getClaimAsStringList("roles");
        if (roles == null || roles.isEmpty()) {
            return "ROLE_CUSTOMER";
        }
        if (roles.contains("ROLE_ADMIN")) {
            return "ROLE_ADMIN";
        } else if (roles.contains("ROLE_MANAGER")) {
            return "ROLE_MANAGER";
        }
        return roles.get(0);
    }
}
