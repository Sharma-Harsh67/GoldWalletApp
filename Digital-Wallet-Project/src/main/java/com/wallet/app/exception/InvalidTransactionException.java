package com.wallet.app.exception;

class InvalidTransactionException extends WalletApplicationException {
    public InvalidTransactionException(String message) {
        super("Invalid transaction: " + message, "INVALID_TRANSACTION");
    }
}