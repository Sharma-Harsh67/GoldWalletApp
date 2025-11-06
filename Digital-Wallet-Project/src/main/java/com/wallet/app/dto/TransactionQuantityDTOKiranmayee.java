package com.wallet.app.dto;

public class TransactionQuantityDTOKiranmayee {
    private String userName;
    private Double quantity;

    public TransactionQuantityDTOKiranmayee(String userName, Double quantity) {
        this.userName = userName;
        this.quantity = quantity;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}