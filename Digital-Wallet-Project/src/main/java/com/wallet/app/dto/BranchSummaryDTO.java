package com.wallet.app.dto;

public class BranchSummaryDTO {
    private String branchName;
    private Double quantity;

    public BranchSummaryDTO(String branchName, Double quantity) {
        this.branchName = branchName;
        this.quantity = quantity;
    }

    public String getBranchName() {
        return branchName;
    }

    public Double getQuantity() {
        return quantity;
    }
}