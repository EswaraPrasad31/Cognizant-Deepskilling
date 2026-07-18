package com.secure.platform.product.service.impl;

import com.secure.platform.product.dto.ProductRequest;
import com.secure.platform.product.dto.ProductResponse;
import com.secure.platform.product.entity.Product;
import com.secure.platform.product.mapper.ProductMapper;
import com.secure.platform.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    private final ProductMapper productMapper = new ProductMapper();

    private ProductServiceImpl productService;

    private Product product;
    private UUID productId;

    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl(productRepository, productMapper);
        productId = UUID.randomUUID();
        product = Product.builder()
                .id(productId)
                .name("Laptop")
                .description("Work Laptop")
                .price(new BigDecimal("999.99"))
                .quantity(10)
                .build();
    }

    @Test
    void addProduct_Success() {
        ProductRequest request = ProductRequest.builder()
                .name("Laptop")
                .description("Work Laptop")
                .price(new BigDecimal("999.99"))
                .quantity(10)
                .build();

        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductResponse response = productService.addProduct(request);

        assertNotNull(response);
        assertEquals(product.getName(), response.getName());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void updateProduct_Success() {
        ProductRequest request = ProductRequest.builder()
                .name("New Laptop Name")
                .description("Updated Description")
                .price(new BigDecimal("1099.99"))
                .quantity(5)
                .build();

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductResponse response = productService.updateProduct(productId, request);

        assertNotNull(response);
        assertEquals("New Laptop Name", product.getName());
        assertEquals(5, product.getQuantity());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void deleteProduct_Success() {
        when(productRepository.existsById(productId)).thenReturn(true);
        doNothing().when(productRepository).deleteById(productId);

        assertDoesNotThrow(() -> productService.deleteProduct(productId));
        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    void deleteProduct_ThrowsException_WhenNotFound() {
        when(productRepository.existsById(productId)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> productService.deleteProduct(productId));
        verify(productRepository, never()).deleteById(any(UUID.class));
    }

    @Test
    void reduceStock_Success() {
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        assertDoesNotThrow(() -> productService.reduceStock(productId, 3));
        assertEquals(7, product.getQuantity());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void reduceStock_ThrowsException_WhenInsufficientStock() {
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        assertThrows(IllegalArgumentException.class, () -> productService.reduceStock(productId, 15));
        verify(productRepository, never()).save(any(Product.class));
    }
}
