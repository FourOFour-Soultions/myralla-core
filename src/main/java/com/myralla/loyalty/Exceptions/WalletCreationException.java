package com.myralla.loyalty.Exceptions;

public class WalletCreationException extends RuntimeException {
    
    public WalletCreationException(String message){
        super(message);
    }

    public WalletCreationException(String message, Throwable cause){
        super(message, cause);
    }

}
