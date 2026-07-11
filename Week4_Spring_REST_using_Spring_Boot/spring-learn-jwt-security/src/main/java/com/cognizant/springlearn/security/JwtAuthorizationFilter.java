package com.cognizant.springlearn.security;

import com.cognizant.springlearn.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

    private final JwtTokenProvider tokenProvider;
    private final UserDetailsService userDetailsService;
    private final ObjectMapper objectMapper;

    @Autowired
    public JwtAuthorizationFilter(JwtTokenProvider tokenProvider, @Lazy UserDetailsService userDetailsService) {
        this.tokenProvider = tokenProvider;
        this.userDetailsService = userDetailsService;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.findAndRegisterModules(); // Register support for java.time (LocalDateTime)
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        LOGGER.info("START - JwtAuthorizationFilter processing request: {}", request.getRequestURI());

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            LOGGER.info("No JWT Bearer token found in request headers. Proceeding with filter chain.");
            LOGGER.info("END - JwtAuthorizationFilter");
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        try {
            if (tokenProvider.validateToken(token)) {
                String username = tokenProvider.getUsernameFromToken(token);
                LOGGER.debug("Valid JWT for username: {}", username);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    LOGGER.debug("User {} successfully authenticated via JWT", username);
                }
            } else {
                LOGGER.warn("JWT token validation failed.");
                writeErrorResponse(response, "Unauthorized", "Malformed or invalid JWT token");
                return;
            }
        } catch (ExpiredJwtException e) {
            LOGGER.warn("JWT token has expired.");
            writeErrorResponse(response, "Unauthorized", "JWT token has expired");
            return;
        } catch (JwtException e) {
            LOGGER.warn("Malformed or invalid JWT token: {}", e.getMessage());
            writeErrorResponse(response, "Unauthorized", "Malformed or invalid JWT token");
            return;
        }

        LOGGER.info("END - JwtAuthorizationFilter");
        filterChain.doFilter(request, response);
    }

    private void writeErrorResponse(HttpServletResponse response, String error, String message) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED.value(),
                error,
                Collections.singletonList(message)
        );

        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
