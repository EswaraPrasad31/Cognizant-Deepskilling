package com.secure.platform.order.mapper;

import com.secure.platform.order.dto.OrderRequest;
import com.secure.platform.order.dto.OrderResponse;
import com.secure.platform.order.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order toEntity(OrderRequest request) {
        if (request == null) {
            return null;
        }
        return Order.builder()
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .status("PLACED")
                .build();
    }

    public OrderResponse toDto(Order order) {
        if (order == null) {
            return null;
        }
        return OrderResponse.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .productId(order.getProductId())
                .quantity(order.getQuantity())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }
}
