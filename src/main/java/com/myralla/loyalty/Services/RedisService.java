package com.myralla.loyalty.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * Save a balance for a specific tenant and account.
     * @param tenantId The tenant's unique identifier.
     * @param accountId The account's unique identifier.
     * @param balance The balance to save.
     */
    public void saveBalance(String tenantId, String accountId, Double balance) {
        String key = generateBalanceKey(tenantId, accountId);
        redisTemplate.opsForValue().set(key, balance);
    }

    /**
     * Retrieve the balance for a specific tenant and account.
     * @param tenantId The tenant's unique identifier.
     * @param accountId The account's unique identifier.
     * @return The balance, or null if not found.
     */
    public Double getBalance(String tenantId, String accountId) {
        String key = generateBalanceKey(tenantId, accountId);
        return (Double) redisTemplate.opsForValue().get(key);
    }

    /**
     * Delete the balance for a specific tenant and account.
     * @param tenantId The tenant's unique identifier.
     * @param accountId The account's unique identifier.
     */
    public void deleteBalance(String tenantId, String accountId) {
        String key = generateBalanceKey(tenantId, accountId);
        redisTemplate.delete(key);
    }

    /**
     * Generate a Redis key for storing a wallet balance.
     * Format: tenant:<tenant_id>:wallet:<account_id>:balance
     * @param tenantId The tenant's unique identifier.
     * @param accountId The account's unique identifier.
     * @return The generated key.
     */
    private String generateBalanceKey(String tenantId, String accountId) {
        return String.format("tenant:%s:wallet:%s:balance", tenantId, accountId);
    }
}
