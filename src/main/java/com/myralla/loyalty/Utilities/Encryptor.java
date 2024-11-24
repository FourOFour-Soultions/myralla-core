package com.myralla.loyalty.Utilities;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Base64;

public class Encryptor {
private static final String ALGORITHM = "AES";
private static final int API_KEY_LENGTH = 32;
private static final int ORG_KEY_LENGTH = 6;

    public static String generateRandomKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] apiKeyBytes = new byte[API_KEY_LENGTH];
        secureRandom.nextBytes(apiKeyBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(apiKeyBytes);
    }

    public static String encrypt(String data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
    
    public static String generateOrgKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] apiKeyBytes = new byte[ORG_KEY_LENGTH];
        secureRandom.nextBytes(apiKeyBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(apiKeyBytes);
    }

}
