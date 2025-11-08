package com.wallet.app.dto;

public class BranchSummaryDTO {
    private Long vendorId;
    private Long branchId;
    private String branchName;
    private Double quantity;

    // Constructor with all fields
    public BranchSummaryDTO(Long vendorId, Long branchId, String branchName, Double quantity) {
        this.vendorId = vendorId;
        this.branchId = branchId;
        this.branchName = branchName;
        this.quantity = quantity;
    }

    // Constructor without quantity (for basic branch info)
    public BranchSummaryDTO(Long vendorId, Long branchId, String branchName) {
        this.vendorId = vendorId;
        this.branchId = branchId;
        this.branchName = branchName;
    }

    // Getters and Setters
    public Long getVendorId() { 
        return vendorId; 
    }
    
    public void setVendorId(Long vendorId) { 
        this.vendorId = vendorId; 
    }

    public Long getBranchId() { 
        return branchId; 
    }
    
    public void setBranchId(Long branchId) { 
        this.branchId = branchId; 
    }

    public String getBranchName() { 
        return branchName; 
    }
    
    public void setBranchName(String branchName) { 
        this.branchName = branchName; 
    }

    public Double getQuantity() { 
        return quantity; 
    }
    
    public void setQuantity(Double quantity) { 
        this.quantity = quantity; 
    }

    @Override
    public String toString() {
        return "BranchSummaryDTO{" +
                "vendorId=" + vendorId +
                ", branchId=" + branchId +
                ", branchName='" + branchName + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}