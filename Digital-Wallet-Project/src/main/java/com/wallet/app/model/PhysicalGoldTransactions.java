package com.digitalgoldwallet.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;



@Entity
@Table(name = "physical_gold_transactions")
public class PhysicalGoldTransactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Integer transactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
   
    private Users user;

    @Column(name = "branch_id", nullable = false)
    private Integer branchId;

    @Column(name = "quantity", precision = 8, scale = 2, nullable = false)
    private Double quantity;

    @Column(name = "delivery_address_id", nullable = false)
    private Integer deliveryAddressId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    
    public PhysicalGoldTransactions() {}

    
    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Integer getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(Integer deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}