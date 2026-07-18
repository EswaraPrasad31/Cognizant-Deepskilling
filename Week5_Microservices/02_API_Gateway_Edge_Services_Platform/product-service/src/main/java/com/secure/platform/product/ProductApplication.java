package com.secure.platform.product;

import com.secure.platform.product.entity.Product;
import com.secure.platform.product.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

    @Bean
    public CommandLineRunner seedProducts(ProductRepository productRepository) {
        return args -> {
            if (productRepository.count() == 0) {
                Product laptop = Product.builder()
                        .name("Enterprise Laptop")
                        .description("High-performance developer work laptop")
                        .price(new BigDecimal("1299.99"))
                        .quantity(50)
                        .build();
                productRepository.save(laptop);

                Product mouse = Product.builder()
                        .name("Wireless Mouse")
                        .description("Ergonomic wireless mouse with silent clicks")
                        .price(new BigDecimal("49.99"))
                        .quantity(150)
                        .build();
                productRepository.save(mouse);

                Product keyboard = Product.builder()
                        .name("Mechanical Keyboard")
                        .description("Backlit tactile keyboard with cherry-mx switches")
                        .price(new BigDecimal("99.99"))
                        .quantity(100)
                        .build();
                productRepository.save(keyboard);
            }
        };
    }
}
