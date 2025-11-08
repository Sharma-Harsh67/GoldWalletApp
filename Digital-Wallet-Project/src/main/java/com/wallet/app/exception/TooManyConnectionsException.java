package com.wallet.app.exception;

public class TooManyConnectionsException extends WalletApplicationException {
    public TooManyConnectionsException() {
        super("Too many active database connections. Please try again later.", 
            "TOO_MANY_CONNECTIONS");
    }
    
    public TooManyConnectionsException(int currentConnections, int maxConnections) {
        super(String.format("Database connection pool exhausted. Active: %d, Max: %d", 
            currentConnections, maxConnections), "TOO_MANY_CONNECTIONS");
    }
}

