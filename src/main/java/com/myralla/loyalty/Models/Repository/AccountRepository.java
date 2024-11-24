package com.myralla.loyalty.Models.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.myralla.loyalty.Models.Entity.Accounts;

public interface AccountRepository extends JpaRepository<Accounts, Long> {

    boolean existsByTenantId(String tenantId);

}
