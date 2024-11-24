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

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private WalletService walletService;

    public ResponseEntity<Object> createAccount(Map<String, String> body) {
        String tenantId = body.get("tenantId");
        String orgKey = body.get("orgKey");

        if (tenantId == null || tenantId.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "externalKey is missing"));
        }
        if (orgKey == null || orgKey.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "orgKey is missing"));
        }
        if (accountRepository.existsByTenantId(tenantId)) {
            return ResponseEntity.badRequest().body(Map.of("message", "Account already exists"));
        }

        Accounts newAccount = new Accounts();
        newAccount.setTenantId(tenantId);
        newAccount.setOrgKey(orgKey);
        newAccount.setFirstName(body.get("firstName"));
        newAccount.setLastName(body.get("lastName"));
        newAccount.setCreatedAt(java.time.LocalDateTime.now().toString());
        newAccount.setUpdatedAt(java.time.LocalDateTime.now().toString());
        accountRepository.saveAndFlush(newAccount);

        log.info("Assigned Id to new Account: ", newAccount.getId().toString());
        // Auto Create Wallet Upon Account Creation
        HashMap<String, String> walletBody = new HashMap<>();
        walletBody.put("tenantId", tenantId);
        walletBody.put("accountId", newAccount.getId().toString());
        walletBody.put("balance", "0.0");
        walletBody.put("createdAt", java.time.LocalDateTime.now().toString());
        walletBody.put("updatedAt", java.time.LocalDateTime.now().toString());
    
        walletService.createWallet(walletBody);
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
