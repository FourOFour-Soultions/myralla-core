package com.myralla.loyalty.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.myralla.loyalty.Models.Accounts;

public interface AccountRepository extends JpaRepository<Accounts, Long> {

    boolean existsByTenantId(String tenantId);

}
