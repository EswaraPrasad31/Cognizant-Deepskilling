package com.secure.platform.order.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.UUID;

@FeignClient(name = "inventory-service")
public interface InventoryClient {
    @GetMapping("/inventory/{productId}")
    Object getStockByProductId(@PathVariable("productId") UUID productId);

    @PutMapping("/inventory/{productId}/reduce")
    Object reduceStock(@PathVariable("productId") UUID productId, @RequestParam("quantity") Integer quantity);

    @PutMapping("/inventory/{productId}/increase")
    Object increaseStock(@PathVariable("productId") UUID productId, @RequestParam("quantity") Integer quantity);
}
