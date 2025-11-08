package com.wallet.app.exception;

// ==================== DATABASE CONNECTION EXCEPTIONS ====================

public class DatabaseConnectionException extends WalletApplicationException {
    public DatabaseConnectionException(String message) {
        super("Database connection failed: " + message, "DB_CONNECTION_ERROR");
    }
    
    public DatabaseConnectionException(String message, Throwable cause) {
        super("Database connection failed: " + message, "DB_CONNECTION_ERROR", cause);
    }
}
