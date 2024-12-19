package com.myralla.loyalty.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.myralla.loyalty.Models.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
//    boolean existsByAccountId(String accountId);

    Wallet findByTenantIdAndAccountId(String tenantId, String accountId);
}
