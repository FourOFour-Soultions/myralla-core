package com.myralla.loyalty.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myralla.loyalty.Exceptions.WalletCreationException;
import com.myralla.loyalty.Models.Wallet;
import java.util.Map;
import com.myralla.loyalty.Repository.WalletRepository;

@Service
public class WalletService {
    
    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private RedisService redisService;
    
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

    public void updateWalletBalance(Map <String, String> body) {
        try {
            String tenantId = body.get("tenantId");
            String accountId = body.get("accountId");
            Double balance = Double.parseDouble(body.get("balance"));
            String updatedAt = body.get("updatedAt");
    
            Wallet wallet = walletRepository.findByTenantIdAndAccountId(tenantId, accountId);
            wallet.setBalance(balance);
            wallet.setUpdatedAt(updatedAt);
            walletRepository.saveAndFlush(wallet);
            
            redisService.saveBalance(tenantId, accountId, balance);
            
        } catch(Exception e){
            throw new WalletCreationException("Failed To Update Wallet Balance" + e.getMessage(), e);
        }
    }


}
