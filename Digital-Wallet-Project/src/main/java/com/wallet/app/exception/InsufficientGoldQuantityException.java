package com.wallet.app.exception;

class InsufficientGoldQuantityException extends WalletApplicationException {
    public InsufficientGoldQuantityException(Double required, Double available) {
        super(String.format("Insufficient gold quantity. Required: %.4f g, Available: %.4f g", 
            required, available), "INSUFFICIENT_GOLD_QUANTITY");
    }
}
