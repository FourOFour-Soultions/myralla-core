package com.myralla.loyalty.Services;

import com.myralla.loyalty.Models.Entity.Accounts;
import com.myralla.loyalty.Models.Entity.Tenants;
import com.myralla.loyalty.Models.Repository.AccountRepository;
import com.myralla.loyalty.Models.Repository.TenantRepository;
import com.myralla.loyalty.Utilities.Encryptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TenantRepository tenantRepository;

    public ResponseEntity<Object> linkAccount(Map<String, String> body) {
        String externalKey = body.get("externalKey");
        String orgKey = body.get("orgKey");

        if (externalKey == null || externalKey.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "externalKey is missing"));
        }
        if (orgKey == null || orgKey.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "orgKey is missing"));
        }
        if (accountRepository.existsByExternalKey(externalKey)) {
            return ResponseEntity.badRequest().body(Map.of("message", "Account already exists"));
        }

        Accounts newAccount = new Accounts();
        newAccount.setExternalKey(externalKey);
        newAccount.setOrgKey(orgKey);
        newAccount.setCreatedAt(java.time.LocalDateTime.now().toString());
        newAccount.setUpdatedAt(java.time.LocalDateTime.now().toString());
        accountRepository.saveAndFlush(newAccount);
        return ResponseEntity.status(201).body(Map.of("message", "linkAccount success"));
    }

    public ResponseEntity<Object> createTenant(Map<String, String> body) throws Exception {
        String orgKey = body.get("orgKey");
        String name = body.get("name");
        if (orgKey == null || tenantRepository.existsByOrgKey(orgKey)) {
            return ResponseEntity.badRequest().body(Map.of("message", "Tenant already exists or orgKey is missing"));
        }
        if (name == null || name.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Name is missing"));
        }
        Tenants newTenant = new Tenants();
        newTenant.setName(name);
        newTenant.setApiKey(Encryptor.generateRandomKey());
        newTenant.setApiSecret(Encryptor.generateRandomKey());
        newTenant.setOrgKey(orgKey);
        newTenant.setDescription(body.getOrDefault("description", "No Description Provided Upon Registration"));
        newTenant.setCreatedAt(java.time.LocalDateTime.now().toString());
        newTenant.setUpdatedAt(java.time.LocalDateTime.now().toString());
        tenantRepository.saveAndFlush(newTenant);
        return ResponseEntity.status(201).body(Map.of("message", "Tenant Registered"));
    }
}
