package com.secure.platform.billing.service;
import com.secure.platform.billing.dto.*;
import java.util.UUID;

public interface BillingService {
    BillingResponse generateBill(BillingRequest request);
    BillingResponse getBillById(UUID id);
}
