package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.security.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthenticationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    @Autowired
    public AuthenticationController(UserDetailsService userDetailsService,
                                    PasswordEncoder passwordEncoder,
                                    JwtTokenProvider tokenProvider) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @GetMapping("/authenticate")
    public ResponseEntity<Map<String, String>> authenticate(HttpServletRequest request) {
        LOGGER.info("START - GET /authenticate");

        String authHeader = request.getHeader("Authorization");
        LOGGER.info("Authorization Header received: {}", authHeader != null ? "Present" : "Missing");

        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            LOGGER.error("Missing or malformed Authorization Basic header");
            throw new BadCredentialsException("Missing or malformed Basic authorization credentials");
        }

        try {
            // Extract and decode credentials
            String base64Credentials = authHeader.substring(6).trim();
            byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(decodedBytes, StandardCharsets.UTF_8);
            String[] values = credentials.split(":", 2);

            if (values.length != 2) {
                LOGGER.error("Malformed Base64 decoded credentials");
                throw new BadCredentialsException("Invalid Basic authorization structure");
            }

            String username = values[0];
            String password = values[1];
            LOGGER.info("Attempting login validation for username: {}", username);

            // Fetch configured user and check passwords
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                LOGGER.error("Credentials password mismatch for user: {}", username);
                throw new BadCredentialsException("Invalid username or password");
            }

            // Generate JWT
            LOGGER.info("Authentication successful. Triggering token generation.");
            String token = tokenProvider.generateToken(username);
            LOGGER.info("Token generated successfully for user: {}", username);

            Map<String, String> response = new HashMap<>();
            response.put("token", token);

            LOGGER.info("END - GET /authenticate");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            LOGGER.error("Authentication exception encountered: {}", e.getMessage());
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
