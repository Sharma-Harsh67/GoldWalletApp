package com.wallet.app.exception;

public class DatabaseTimeoutException extends WalletApplicationException {
    public DatabaseTimeoutException(String operation) {
        super("Database operation timed out: " + operation, "DB_TIMEOUT");
    }
    
    public DatabaseTimeoutException(String operation, Throwable cause) {
        super("Database operation timed out: " + operation, "DB_TIMEOUT", cause);
    }
}