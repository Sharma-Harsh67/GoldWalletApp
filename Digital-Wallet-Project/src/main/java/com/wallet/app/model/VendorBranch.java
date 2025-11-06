package com.wallet.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "vendor_branches")
public class VendorBranch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "vendor_id")
    private Long vendorId;

    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "branch_name")
    private String branch_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id", insertable = false, updatable = false)
    @JsonIgnore
    private Vendor vendor;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PhysicalGoldTransactions> transactions;

    // Getters and Setters
    public Long getBranchId() { return branchId; }
    public void setBranchId(Long branchId) { this.branchId = branchId; }

    public Long getVendorId() { return vendorId; }
    public void setVendorId(Long vendorId) { this.vendorId = vendorId; }

    public Long getAddressId() { return addressId; }
    public void setAddressId(Long addressId) { this.addressId = addressId; }

    public Double getQuantity() { return quantity; }
    public void setQuantity(Double quantity) { this.quantity = quantity; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getBranch_name() { return branch_name; }
    public void setBranch_name(String branch_name) { this.branch_name = branch_name; }

    public Vendor getVendor() { return vendor; }
    public void setVendor(Vendor vendor) { this.vendor = vendor; }

    public List<PhysicalGoldTransactions> getTransactions() { return transactions; }
    public void setTransactions(List<PhysicalGoldTransactions> transactions) { this.transactions = transactions; }
}