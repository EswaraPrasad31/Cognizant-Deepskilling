package com.secure.platform.product.service;

import com.secure.platform.product.dto.ProductRequest;
import com.secure.platform.product.dto.ProductResponse;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductResponse addProduct(ProductRequest request);
    ProductResponse updateProduct(UUID id, ProductRequest request);
    void deleteProduct(UUID id);
    List<ProductResponse> getAllProducts();
    List<ProductResponse> searchProducts(String name);
    ProductResponse getProductById(UUID id);
    void reduceStock(UUID id, Integer quantity);
}
