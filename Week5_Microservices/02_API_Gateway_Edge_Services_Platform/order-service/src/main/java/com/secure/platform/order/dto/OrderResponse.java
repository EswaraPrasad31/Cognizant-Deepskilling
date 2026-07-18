package com.secure.platform.order.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class OrderResponse {
    private UUID id;
    private UUID userId;
    private UUID productId;
    private Integer quantity;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public OrderResponse() {}

    public OrderResponse(UUID id, UUID userId, UUID productId, Integer quantity, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }
    public UUID getProductId() { return productId; }
    public void setProductId(UUID productId) { this.productId = productId; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public static OrderResponseBuilder builder() {
        return new OrderResponseBuilder();
    }

    public static class OrderResponseBuilder {
        private UUID id;
        private UUID userId;
        private UUID productId;
        private Integer quantity;
        private String status;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public OrderResponseBuilder id(UUID id) { this.id = id; return this; }
        public OrderResponseBuilder userId(UUID userId) { this.userId = userId; return this; }
        public OrderResponseBuilder productId(UUID productId) { this.productId = productId; return this; }
        public OrderResponseBuilder quantity(Integer quantity) { this.quantity = quantity; return this; }
        public OrderResponseBuilder status(String status) { this.status = status; return this; }
        public OrderResponseBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public OrderResponseBuilder updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }

        public OrderResponse build() {
            return new OrderResponse(id, userId, productId, quantity, status, createdAt, updatedAt);
        }
    }
}
