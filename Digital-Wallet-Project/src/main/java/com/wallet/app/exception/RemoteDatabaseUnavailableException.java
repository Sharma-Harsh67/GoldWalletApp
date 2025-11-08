package com.wallet.app.exception;

class RemoteDatabaseUnavailableException extends WalletApplicationException {
    public RemoteDatabaseUnavailableException(String dbHost) {
        super("Remote database is unavailable at: " + dbHost + 
            ". Please check network connectivity and ensure the database server is running.", 
            "REMOTE_DB_UNAVAILABLE");
    }
    
    public RemoteDatabaseUnavailableException(String dbHost, Throwable cause) {
        super("Remote database is unavailable at: " + dbHost, 
            "REMOTE_DB_UNAVAILABLE", cause);
    }
}

