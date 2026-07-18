package com.secure.platform.order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public class OrderRequest {

    @NotNull(message = "Product ID is required")
    private UUID productId;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    public OrderRequest() {}

    public OrderRequest(UUID productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    // Getters and Setters
    public UUID getProductId() { return productId; }
    public void setProductId(UUID productId) { this.productId = productId; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public static OrderRequestBuilder builder() {
        return new OrderRequestBuilder();
    }

    public static class OrderRequestBuilder {
        private UUID productId;
        private Integer quantity;

        public OrderRequestBuilder productId(UUID productId) { this.productId = productId; return this; }
        public OrderRequestBuilder quantity(Integer quantity) { this.quantity = quantity; return this; }

        public OrderRequest build() {
            return new OrderRequest(productId, quantity);
        }
    }
}
