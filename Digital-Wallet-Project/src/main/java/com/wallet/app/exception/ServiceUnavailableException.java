package com.wallet.app.exception;

class ServiceUnavailableException extends WalletApplicationException {
    public ServiceUnavailableException(String serviceName) {
        super("Service temporarily unavailable: " + serviceName, "SERVICE_UNAVAILABLE");
    }
    
    public ServiceUnavailableException(String serviceName, Throwable cause) {
        super("Service temporarily unavailable: " + serviceName, "SERVICE_UNAVAILABLE", cause);
    }
}
