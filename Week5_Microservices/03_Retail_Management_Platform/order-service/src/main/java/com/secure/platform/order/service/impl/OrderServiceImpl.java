package com.secure.platform.order.service.impl;
import com.secure.platform.order.client.*;
import com.secure.platform.order.dto.*;
import com.secure.platform.order.entity.Order;
import com.secure.platform.order.repository.OrderRepository;
import com.secure.platform.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    
    // Feign Clients
    private final UserClient userClient;
    private final ProductClient productClient;
    private final InventoryClient inventoryClient;
    private final PaymentClient paymentClient;
    private final BillingClient billingClient;
    
    // WebClient
    private final WebClient.Builder webClientBuilder;

    @Value("${client.mode:feign}")
    private String clientMode;

    @Override
    public OrderResponse placeOrder(OrderRequest request) {
        log.info("Placing order using mode: {}", clientMode);
        
        // 1. Verify User exists
        if ("feign".equalsIgnoreCase(clientMode)) {
            userClient.getUserById(request.getUserId());
        } else {
            webClientBuilder.build().get()
                    .uri("http://user-service/users/" + request.getUserId())
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
        }

        // 2. Verify Product price
        if ("feign".equalsIgnoreCase(clientMode)) {
            productClient.getProductById(request.getProductId());
        } else {
            webClientBuilder.build().get()
                    .uri("http://product-service/products/" + request.getProductId())
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
        }

        // 3. Check and reduce stock in Inventory Service
        if ("feign".equalsIgnoreCase(clientMode)) {
            inventoryClient.reduceStock(request.getProductId(), request.getQuantity());
        } else {
            webClientBuilder.build().put()
                    .uri("http://inventory-service/inventory/" + request.getProductId() + "/reduce?quantity=" + request.getQuantity())
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
        }

        // 4. Save initial order
        Order order = Order.builder()
                .userId(request.getUserId())
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .status("PLACED")
                .build();
        order = orderRepository.save(order);

        // 5. Process Payment (with Resilience4j in Payment Service)
        Map<String, Object> payReq = new HashMap<>();
        payReq.put("orderId", order.getId().toString());
        // Simulating amount of 100 per unit quantity
        BigDecimal amount = BigDecimal.valueOf(request.getQuantity() * 100);
        payReq.put("amount", amount);
        
        Object payRes = null;
        if ("feign".equalsIgnoreCase(clientMode)) {
            payRes = paymentClient.processPayment(payReq);
        } else {
            payRes = webClientBuilder.build().post()
                    .uri("http://payment-service/payments")
                    .bodyValue(payReq)
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
        }

        // 6. Generate Bill
        Map<String, Object> billReq = new HashMap<>();
        billReq.put("orderId", order.getId().toString());
        billReq.put("amount", amount);
        if ("feign".equalsIgnoreCase(clientMode)) {
            billingClient.generateBill(billReq);
        } else {
            webClientBuilder.build().post()
                    .uri("http://billing-service/billing")
                    .bodyValue(billReq)
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
        }

        return OrderResponse.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .productId(order.getProductId())
                .quantity(order.getQuantity())
                .status(order.getStatus())
                .build();
    }

    @Override
    public void cancelOrder(UUID orderId) {
        log.info("Cancelling order with ID: {}", orderId);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        order.setStatus("CANCELLED");
        orderRepository.save(order);

        // Restore stock in Inventory
        if ("feign".equalsIgnoreCase(clientMode)) {
            inventoryClient.increaseStock(order.getProductId(), order.getQuantity());
        } else {
            webClientBuilder.build().put()
                    .uri("http://inventory-service/inventory/" + order.getProductId() + "/increase?quantity=" + order.getQuantity())
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
        }
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        log.info("Getting all orders");
        return orderRepository.findAll().stream()
                .map(o -> OrderResponse.builder()
                        .id(o.getId())
                        .userId(o.getUserId())
                        .productId(o.getProductId())
                        .quantity(o.getQuantity())
                        .status(o.getStatus())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse getOrderById(UUID id) {
        log.info("Getting order by id: {}", id);
        return orderRepository.findById(id)
                .map(o -> OrderResponse.builder()
                        .id(o.getId())
                        .userId(o.getUserId())
                        .productId(o.getProductId())
                        .quantity(o.getQuantity())
                        .status(o.getStatus())
                        .build())
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }
}
