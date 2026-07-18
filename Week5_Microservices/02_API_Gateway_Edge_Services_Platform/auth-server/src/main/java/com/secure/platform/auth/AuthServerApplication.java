package com.secure.platform.auth;

import com.secure.platform.auth.entity.User;
import com.secure.platform.auth.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }

    @Bean
    public CommandLineRunner seedData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.count() == 0) {
                // Seed Admin
                User admin = User.builder()
                        .name("System Admin")
                        .email("admin@example.com")
                        .password(passwordEncoder.encode("admin123"))
                        .role("ADMIN")
                        .build();
                userRepository.save(admin);

                // Seed Customer
                User customer = User.builder()
                        .name("John Customer")
                        .email("customer@example.com")
                        .password(passwordEncoder.encode("customer123"))
                        .role("CUSTOMER")
                        .build();
                userRepository.save(customer);

                // Seed Manager
                User manager = User.builder()
                        .name("Jane Manager")
                        .email("manager@example.com")
                        .password(passwordEncoder.encode("manager123"))
                        .role("MANAGER")
                        .build();
                userRepository.save(manager);
            }
        };
    }
}
