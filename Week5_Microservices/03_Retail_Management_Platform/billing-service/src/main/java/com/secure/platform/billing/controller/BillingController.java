package com.secure.platform.billing.controller;
import com.secure.platform.billing.dto.*;
import com.secure.platform.billing.service.BillingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/billing")
@RequiredArgsConstructor
public class BillingController {
    private final BillingService billingService;

    @PostMapping
    public ResponseEntity<BillingResponse> generateBill(@Valid @RequestBody BillingRequest request) {
        return new ResponseEntity<>(billingService.generateBill(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillingResponse> getBillById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(billingService.getBillById(id));
    }
}
