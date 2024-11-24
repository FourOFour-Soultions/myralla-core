package com.myralla.loyalty.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.myralla.loyalty.Exceptions.WalletCreationException;
import com.myralla.loyalty.Models.Entity.Wallet;
import java.util.Map;
import com.myralla.loyalty.Models.Repository.WalletRepository;

@Service
public class WalletService {
    
    @Autowired
    private WalletRepository walletRepository;
    
    public void createWallet(Map <String, String> body) {
        try {
            String tenantId = body.get("tenantId");
            String accountId = body.get("accountId");
            Double balance = Double.parseDouble(body.get("balance"));
            String createdAt = body.get("createdAt");
            String updatedAt = body.get("updatedAt");
    
            Wallet newWallet = new Wallet();
            newWallet.setTenantId(tenantId);
            newWallet.setAccountId(accountId);
            newWallet.setBalance(balance);
            newWallet.setCreatedAt(createdAt);
            newWallet.setUpdatedAt(updatedAt);
            walletRepository.saveAndFlush(newWallet);
            
        } catch(Exception e){
            throw new WalletCreationException("Failed To Create Wallet" + e.getMessage(), e);
        }
    
    }


}
