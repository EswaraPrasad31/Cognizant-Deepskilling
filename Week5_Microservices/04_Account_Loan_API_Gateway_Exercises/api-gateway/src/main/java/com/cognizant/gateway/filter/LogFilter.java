package com.cognizant.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LogFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestPath = exchange.getRequest().getPath().value();
        String requestMethod = exchange.getRequest().getMethod().name();
        
        logger.info("=========================================");
        logger.info("LOG FILTER: Incoming request details:");
        logger.info("Method: {}", requestMethod);
        logger.info("Path: {}", requestPath);
        logger.info("=========================================");
        
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1; // Run before routing filters to ensure logs are written early
    }
}
