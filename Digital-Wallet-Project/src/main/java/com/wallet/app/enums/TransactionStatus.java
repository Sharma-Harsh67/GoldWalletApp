package com.wallet.app.enums;

public enum TransactionStatus {
    SUCCESS("Success"),
    FAILED("Failed");

    private final String value;

    TransactionStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}