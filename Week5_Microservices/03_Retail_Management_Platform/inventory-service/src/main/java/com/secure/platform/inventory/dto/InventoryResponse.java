package com.secure.platform.inventory.dto;
import lombok.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryResponse {
    private UUID id;
    private UUID productId;
    private Integer quantity;
}
