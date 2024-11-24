package com.myralla.loyalty.Models.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.myralla.loyalty.Models.Entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    boolean existsByAccountId(String accountId);

}
