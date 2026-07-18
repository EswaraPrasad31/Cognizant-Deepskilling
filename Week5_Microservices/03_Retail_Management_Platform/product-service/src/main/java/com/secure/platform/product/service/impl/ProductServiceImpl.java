package com.secure.platform.product.service.impl;
import com.secure.platform.product.dto.*;
import com.secure.platform.product.entity.Product;
import com.secure.platform.product.mapper.ProductMapper;
import com.secure.platform.product.repository.ProductRepository;
import com.secure.platform.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        log.info("Creating product: {}", request.getName());
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
        return productMapper.toResponse(productRepository.save(product));
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        log.info("Getting all products");
        return productRepository.findAll().stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse getProductById(UUID id) {
        log.info("Getting product by id: {}", id);
        return productRepository.findById(id)
                .map(productMapper::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    @Override
    public ProductResponse updateProduct(UUID id, ProductRequest request) {
        log.info("Updating product by id: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        return productMapper.toResponse(productRepository.save(product));
    }

    @Override
    public void deleteProduct(UUID id) {
        log.info("Deleting product by id: {}", id);
        productRepository.deleteById(id);
    }
}
