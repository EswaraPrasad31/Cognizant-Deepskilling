package com.secure.platform.inventory.service.impl;
import com.secure.platform.inventory.dto.InventoryResponse;
import com.secure.platform.inventory.entity.Inventory;
import com.secure.platform.inventory.repository.InventoryRepository;
import com.secure.platform.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    @Override
    public List<InventoryResponse> getAllStock() {
        log.info("Getting all inventories");
        return inventoryRepository.findAll().stream()
                .map(inv -> InventoryResponse.builder()
                        .id(inv.getId())
                        .productId(inv.getProductId())
                        .quantity(inv.getQuantity())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public InventoryResponse getStockByProductId(UUID productId) {
        log.info("Getting inventory for product: {}", productId);
        Inventory inv = inventoryRepository.findByProductId(productId)
                .orElseGet(() -> inventoryRepository.save(Inventory.builder()
                        .productId(productId)
                        .quantity(100)  // default seed quantity
                        .build()));
        return InventoryResponse.builder()
                .id(inv.getId())
                .productId(inv.getProductId())
                .quantity(inv.getQuantity())
                .build();
    }

    @Override
    public InventoryResponse updateStock(UUID productId, Integer quantity) {
        log.info("Updating inventory for product: {} with quantity: {}", productId, quantity);
        Inventory inv = inventoryRepository.findByProductId(productId)
                .orElse(Inventory.builder().productId(productId).quantity(0).build());
        inv.setQuantity(quantity);
        inv = inventoryRepository.save(inv);
        return InventoryResponse.builder()
                .id(inv.getId())
                .productId(inv.getProductId())
                .quantity(inv.getQuantity())
                .build();
    }

    @Override
    public InventoryResponse reduceStock(UUID productId, Integer quantity) {
        log.info("Reducing inventory for product: {} by quantity: {}", productId, quantity);
        Inventory inv = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product inventory not found"));
        if (inv.getQuantity() < quantity) {
            throw new IllegalArgumentException("Insufficient stock in inventory");
        }
        inv.setQuantity(inv.getQuantity() - quantity);
        inv = inventoryRepository.save(inv);
        return InventoryResponse.builder()
                .id(inv.getId())
                .productId(inv.getProductId())
                .quantity(inv.getQuantity())
                .build();
    }

    @Override
    public InventoryResponse increaseStock(UUID productId, Integer quantity) {
        log.info("Increasing inventory for product: {} by quantity: {}", productId, quantity);
        Inventory inv = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product inventory not found"));
        inv.setQuantity(inv.getQuantity() + quantity);
        inv = inventoryRepository.save(inv);
        return InventoryResponse.builder()
                .id(inv.getId())
                .productId(inv.getProductId())
                .quantity(inv.getQuantity())
                .build();
    }
}
