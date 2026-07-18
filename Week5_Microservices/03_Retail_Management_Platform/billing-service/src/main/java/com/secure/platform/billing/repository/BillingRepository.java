package com.secure.platform.billing.repository;
import com.secure.platform.billing.entity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface BillingRepository extends JpaRepository<Billing, UUID> {
}
