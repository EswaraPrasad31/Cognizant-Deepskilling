package com.secure.platform.billing.dto;
import lombok.*;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillingResponse {
    private UUID id;
    private UUID orderId;
    private BigDecimal amount;
    private String invoiceNumber;
    private String status;
}
