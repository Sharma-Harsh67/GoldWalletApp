package com.wallet.app.dto;

public class BranchSummaryDTO {
    private Long branchId;
    private Double quantity;
    private String branch_name;

    public BranchSummaryDTO(Long branchId, Double quantity, String branch_name) {
        this.branchId = branchId;
        this.quantity = quantity;
        this.branch_name = branch_name;
    }

    public Long getBranchId() { return branchId; }
    public void setBranchId(Long branchId) { this.branchId = branchId; }

    public Double getQuantity() { return quantity; }
    public void setQuantity(Double quantity) { this.quantity = quantity; }

    public String getBranch_name() { return branch_name; }
    public void setBranch_name(String branch_name) { this.branch_name = branch_name; }
}
