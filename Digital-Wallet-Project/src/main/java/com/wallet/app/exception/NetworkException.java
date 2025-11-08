package com.wallet.app.exception;

class NetworkException extends WalletApplicationException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NetworkException(String message) {
        super("Network error: " + message, "NETWORK_ERROR");
    }
    
    public NetworkException(String message, Throwable cause) {
        super("Network error: " + message, "NETWORK_ERROR", cause);
    }
}

