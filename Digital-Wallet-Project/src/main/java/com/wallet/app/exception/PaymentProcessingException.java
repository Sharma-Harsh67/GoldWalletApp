package com.wallet.app.exception;

class PaymentProcessingException extends WalletApplicationException {
    public PaymentProcessingException(String message) {
        super("Payment processing failed: " + message, "PAYMENT_PROCESSING_ERROR");
    }
    
    public PaymentProcessingException(String message, Throwable cause) {
        super("Payment processing failed: " + message, "PAYMENT_PROCESSING_ERROR", cause);
    }
}

