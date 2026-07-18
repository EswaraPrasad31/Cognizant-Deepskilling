package com.secure.platform.product.service.impl;
import com.secure.platform.product.dto.*;
import com.secure.platform.product.entity.Product;
import com.secure.platform.product.mapper.ProductMapper;
import com.secure.platform.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {
    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private ProductRepository productRepository;
    private final ProductMapper productMapper = new ProductMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        try {
            java.lang.reflect.Field field = ProductServiceImpl.class.getDeclaredField("productMapper");
            field.setAccessible(true);
            field.set(productService, productMapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetProductById() {
        UUID id = UUID.randomUUID();
        Product product = Product.builder().id(id).name("Mouse").price(BigDecimal.valueOf(25)).build();
        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        ProductResponse response = productService.getProductById(id);
        assertNotNull(response);
        assertEquals("Mouse", response.getName());
    }
}
