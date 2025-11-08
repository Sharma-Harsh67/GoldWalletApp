package com.wallet.app.exception;

/**
 * Base exception class for all custom exceptions in the Digital Wallet Application
 */
public class WalletApplicationException extends RuntimeException {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;
    
    public WalletApplicationException(String message) {
        super(message);
    }
    
    public WalletApplicationException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    
    public WalletApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public WalletApplicationException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
}