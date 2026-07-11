package com.cognizant.springlearn.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

    // 256-bit secure key containing "secretkey" to satisfy the HS256 requirement
    private static final String SECRET_STRING = "secretkey_1234567890_secretkey_1234567890_secretkey";
    private static final long EXPIRATION_MS = 20 * 60 * 1000; // 20 minutes

    private final Key signingKey;

    public JwtTokenProvider() {
        this.signingKey = Keys.hmacShaKeyFor(SECRET_STRING.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String username) {
        LOGGER.info("START - Generating token for user: {}", username);
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_MS);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();

        LOGGER.info("END - Token generated successfully");
        return token;
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            LOGGER.info("START - Validating token");
            Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(token);
            LOGGER.info("END - Token is valid");
            return true;
        } catch (Exception e) {
            LOGGER.error("Token validation failed: {}", e.getMessage());
            return false;
        }
    }
}
