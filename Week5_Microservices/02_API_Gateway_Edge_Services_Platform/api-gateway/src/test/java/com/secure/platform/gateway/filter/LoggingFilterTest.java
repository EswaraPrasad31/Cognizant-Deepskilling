package com.secure.platform.gateway.filter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class LoggingFilterTest {

    private LoggingFilter loggingFilter;
    private ServerWebExchange exchange;
    private GatewayFilterChain chain;
    private ServerHttpRequest request;
    private ServerHttpResponse response;

    @BeforeEach
    public void setUp() {
        loggingFilter = new LoggingFilter();
        exchange = mock(ServerWebExchange.class);
        chain = mock(GatewayFilterChain.class);
        request = mock(ServerHttpRequest.class);
        response = mock(ServerHttpResponse.class);

        when(exchange.getRequest()).thenReturn(request);
        when(exchange.getResponse()).thenReturn(response);
    }

    @Test
    public void testFilterLogsAndDelegates() {
        // Mock request details
        RequestPath requestPath = mock(RequestPath.class);
        when(requestPath.value()).thenReturn("/api/products");
        when(request.getPath()).thenReturn(requestPath);
        when(request.getMethod()).thenReturn(org.springframework.http.HttpMethod.GET);

        // Mock response details
        when(response.getStatusCode()).thenReturn(HttpStatusCode.valueOf(200));

        // Mock chain behavior
        when(chain.filter(any(ServerWebExchange.class))).thenReturn(Mono.empty());

        // Execute and verify reactive stream completion
        Mono<Void> result = loggingFilter.filter(exchange, chain);

        StepVerifier.create(result)
                .expectSubscription()
                .verifyComplete();

        // Verify chain was invoked
        verify(chain, times(1)).filter(exchange);
    }
}
