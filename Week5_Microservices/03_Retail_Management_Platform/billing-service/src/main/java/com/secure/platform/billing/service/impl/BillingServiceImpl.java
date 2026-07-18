package com.secure.platform.billing.service.impl;
import com.secure.platform.billing.dto.*;
import com.secure.platform.billing.entity.Billing;
import com.secure.platform.billing.repository.BillingRepository;
import com.secure.platform.billing.service.BillingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BillingServiceImpl implements BillingService {
    private final BillingRepository billingRepository;

    @Override
    public BillingResponse generateBill(BillingRequest request) {
        log.info("Generating bill for order: {}", request.getOrderId());
        Billing bill = Billing.builder()
                .orderId(request.getOrderId())
                .amount(request.getAmount())
                .invoiceNumber("INV-" + System.currentTimeMillis())
                .status("PAID")
                .build();
        bill = billingRepository.save(bill);
        return BillingResponse.builder()
                .id(bill.getId())
                .orderId(bill.getOrderId())
                .amount(bill.getAmount())
                .invoiceNumber(bill.getInvoiceNumber())
                .status(bill.getStatus())
                .build();
    }

    @Override
    public BillingResponse getBillById(UUID id) {
        log.info("Getting bill with ID: {}", id);
        Billing bill = billingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bill not found"));
        return BillingResponse.builder()
                .id(bill.getId())
                .orderId(bill.getOrderId())
                .amount(bill.getAmount())
                .invoiceNumber(bill.getInvoiceNumber())
                .status(bill.getStatus())
                .build();
    }
}
