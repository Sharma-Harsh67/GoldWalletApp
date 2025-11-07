package com.wallet.app.enums;

public enum TransactionType {
    BUY("Buy"),
    SELL("Sell"),
    CONVERT_TO_PHYSICAL("Convert to Physical");

    private final String value;

    TransactionType(String value) {
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
