package com.secure.platform.order.dto;
import lombok.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    private UUID id;
    private UUID userId;
    private UUID productId;
    private Integer quantity;
    private String status;
}
