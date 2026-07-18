package com.secure.platform.order.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "billing-service")
public interface BillingClient {
    @PostMapping("/billing")
    Object generateBill(@RequestBody Object request);
}
