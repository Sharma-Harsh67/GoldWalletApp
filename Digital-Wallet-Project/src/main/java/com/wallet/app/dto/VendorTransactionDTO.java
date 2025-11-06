package com.wallet.app.dto;

import java.time.LocalDateTime;

public class VendorTransactionDTO {
    private Long transactionId;
    private Integer userId;
    private Long branchId;
    private Long deliveryAddressId;
    private Double quantity;
    private LocalDateTime createdAt;

    public VendorTransactionDTO() {}

    public VendorTransactionDTO(Long transactionId, Integer userId, Long branchId, 
                               Long deliveryAddressId, Double quantity, LocalDateTime createdAt) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.branchId = branchId;
        this.deliveryAddressId = deliveryAddressId;
        this.quantity = quantity;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(Long deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}