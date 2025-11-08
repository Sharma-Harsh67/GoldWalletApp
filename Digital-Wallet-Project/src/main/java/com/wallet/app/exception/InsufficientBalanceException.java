package com.wallet.app.exception;

class InsufficientBalanceException extends WalletApplicationException {

	public InsufficientBalanceException(Double required, Double available) {
        super(String.format("Insufficient balance. Required: %.2f, Available: %.2f", 
            required, available), "INSUFFICIENT_BALANCE");
    }
}

