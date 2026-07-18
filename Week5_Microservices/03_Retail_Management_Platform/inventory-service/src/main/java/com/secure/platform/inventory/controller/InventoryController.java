package com.secure.platform.inventory.controller;
import com.secure.platform.inventory.dto.InventoryResponse;
import com.secure.platform.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<InventoryResponse>> getAllStock() {
        return ResponseEntity.ok(inventoryService.getAllStock());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<InventoryResponse> getStockByProductId(@PathVariable("productId") UUID productId) {
        return ResponseEntity.ok(inventoryService.getStockByProductId(productId));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<InventoryResponse> updateStock(@PathVariable("productId") UUID productId, @RequestParam("quantity") Integer quantity) {
        return ResponseEntity.ok(inventoryService.updateStock(productId, quantity));
    }

    @PutMapping("/{productId}/reduce")
    public ResponseEntity<InventoryResponse> reduceStock(@PathVariable("productId") UUID productId, @RequestParam("quantity") Integer quantity) {
        return ResponseEntity.ok(inventoryService.reduceStock(productId, quantity));
    }

    @PutMapping("/{productId}/increase")
    public ResponseEntity<InventoryResponse> increaseStock(@PathVariable("productId") UUID productId, @RequestParam("quantity") Integer quantity) {
        return ResponseEntity.ok(inventoryService.increaseStock(productId, quantity));
    }
}
