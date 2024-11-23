package com.myralla.loyalty.Models.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.myralla.loyalty.Models.Entity.Tenants;

public interface TenantRepository extends JpaRepository<Tenants, Long> {
    boolean existsByOrgKey(String orgKey);
}