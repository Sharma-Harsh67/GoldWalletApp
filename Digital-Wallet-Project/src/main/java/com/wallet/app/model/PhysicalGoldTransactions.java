package com.wallet.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "physical_gold_transactions")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PhysicalGoldTransactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;  // ✅ From second file

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    @JsonIgnoreProperties({"vendor", "transactions"})
    private VendorBranch branch;  // ✅ From first file

    @Column(name = "quantity", nullable = false)
    private Double quantity;

    @Column(name = "delivery_address_id", nullable = false)
    private Long deliveryAddressId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // ✅ Constructors
    public PhysicalGoldTransactions() {}

    // ✅ Getters and Setters
    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public VendorBranch getBranch() {
        return branch;
    }

    public void setBranch(VendorBranch branch) {
        this.branch = branch;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Long getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(Long deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}