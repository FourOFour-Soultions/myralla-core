package com.myralla.loyalty.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.myralla.loyalty.Models.Tenants;

public interface TenantRepository extends JpaRepository<Tenants, Long> {
    boolean existsByOrgKey(String orgKey);
}