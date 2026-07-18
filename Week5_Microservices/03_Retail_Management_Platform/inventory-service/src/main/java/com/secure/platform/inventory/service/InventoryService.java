package com.secure.platform.inventory.service;
import com.secure.platform.inventory.dto.InventoryResponse;
import java.util.List;
import java.util.UUID;

public interface InventoryService {
    List<InventoryResponse> getAllStock();
    InventoryResponse getStockByProductId(UUID productId);
    InventoryResponse updateStock(UUID productId, Integer quantity);
    InventoryResponse reduceStock(UUID productId, Integer quantity);
    InventoryResponse increaseStock(UUID productId, Integer quantity);
}
