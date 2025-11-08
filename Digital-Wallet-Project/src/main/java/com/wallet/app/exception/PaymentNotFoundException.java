package com.wallet.app.exception;

public class PaymentNotFoundException extends WalletApplicationException {
    public PaymentNotFoundException(Integer paymentId) {
        super("Payment not found with ID: " + paymentId, "PAYMENT_NOT_FOUND");
    }
    
    public PaymentNotFoundException(Integer userId, String message) {
        super("No payments found for user ID: " + userId + ". " + message, "PAYMENT_NOT_FOUND");
    }
}