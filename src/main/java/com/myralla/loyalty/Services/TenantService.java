package com.myralla.loyalty.Services;


import com.myralla.loyalty.Models.Tenants;
import com.myralla.loyalty.Repository.TenantRepository;
import com.myralla.loyalty.Utilities.Encryptor;
import com.myralla.loyalty.dto.TenantDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class TenantService {

    @Autowired
    private TenantRepository tenantRepository;

    public ResponseEntity<Object> createTenant(TenantDTO tenantDTO) throws Exception {


//       TODO: refactor this to use a validator class
        if (tenantDTO.getOrgKey() == null || tenantRepository.existsByOrgKey(tenantDTO.getOrgKey())) {
            return ResponseEntity.badRequest().body(Map.of("message", "Tenant already exists or orgKey is missing"));
        }
        if (tenantDTO.getName() == null || tenantDTO.getName().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Name is missing"));
        }

        Tenants newTenant = new Tenants();
        newTenant.setName(tenantDTO.getName());
        newTenant.setApiKey(Encryptor.generateRandomKey());
        newTenant.setApiSecret(Encryptor.generateRandomKey());
        newTenant.setOrgKey(tenantDTO.getOrgKey());
        newTenant.setDescription(tenantDTO.getDescription());
        newTenant.setCreatedAt(java.time.LocalDateTime.now().toString());
        newTenant.setUpdatedAt(java.time.LocalDateTime.now().toString());

        tenantRepository.saveAndFlush(newTenant);

        return ResponseEntity.status(201).body(Map.of("message", "Tenant Registered"));
    }
}
