package com.secure.platform.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimiterFilter implements GlobalFilter, Ordered {

    private static final Logger log = LoggerFactory.getLogger(RateLimiterFilter.class);

    private static class Bucket {
        private double tokens;
        private long lastRefillTime;

        Bucket(double capacity) {
            this.tokens = capacity;
            this.lastRefillTime = Instant.now().getEpochSecond();
        }

        synchronized boolean tryConsume(double capacity, double refillRate) {
            long now = Instant.now().getEpochSecond();
            long delta = now - lastRefillTime;
            
            if (delta > 0) {
                lastRefillTime = now;
                tokens = Math.min(capacity, tokens + delta * refillRate);
            }
            
            if (tokens >= 1.0) {
                tokens -= 1.0;
                return true;
            }
            return false;
        }
    }

    private final Map<String, Bucket> ipBuckets = new ConcurrentHashMap<>();
    private static final double CAPACITY = 30.0;      // max requests burst
    private static final double REFILL_RATE = 2.0;    // refill 2 tokens per second (120/min)

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String clientIp = exchange.getRequest().getRemoteAddress() != null ?
                exchange.getRequest().getRemoteAddress().getAddress().getHostAddress() : "unknown";

        Bucket bucket = ipBuckets.computeIfAbsent(clientIp, k -> new Bucket(CAPACITY));

        if (!bucket.tryConsume(CAPACITY, REFILL_RATE)) {
            log.warn("Rate limit exceeded for IP: {}", clientIp);
            exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1; // Run immediately after logging
    }
}
