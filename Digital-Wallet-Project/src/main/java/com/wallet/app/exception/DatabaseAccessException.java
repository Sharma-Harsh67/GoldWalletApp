package com.wallet.app.exception;



public class DatabaseAccessException extends WalletApplicationException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DatabaseAccessException(String message) {
        super("Database access error: " + message, "DB_ACCESS_ERROR");
    }
    
    public DatabaseAccessException(String message, Throwable cause) {
        super("Database access error: " + message, "DB_ACCESS_ERROR", cause);
    }
}
