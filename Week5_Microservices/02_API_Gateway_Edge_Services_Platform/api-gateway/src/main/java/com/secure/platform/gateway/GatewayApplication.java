package com.secure.platform.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import com.secure.platform.gateway.config.RandomLoadBalancerConfig;

@SpringBootApplication
@LoadBalancerClients({
    @LoadBalancerClient(name = "auth-server", configuration = RandomLoadBalancerConfig.class),
    @LoadBalancerClient(name = "user-service", configuration = RandomLoadBalancerConfig.class),
    @LoadBalancerClient(name = "product-service", configuration = RandomLoadBalancerConfig.class),
    @LoadBalancerClient(name = "order-service", configuration = RandomLoadBalancerConfig.class)
})
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
