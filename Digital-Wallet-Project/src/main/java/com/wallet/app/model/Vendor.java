package com.wallet.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "vendors")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vendorId;

    @Column(name = "vendor_name", length = 100)
    private String vendorName;

    @Column(name = "contact_email")
    private String contactEmail;

    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore  // ✅ Added to prevent lazy loading issues
    private List<VendorBranch> branches;

    // Getters and Setters
    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<VendorBranch> getBranches() {
        return branches;
    }

    public void setBranches(List<VendorBranch> branches) {
        this.branches = branches;
    }

    @Override
    public String toString() {
        return "Vendor [vendorId=" + vendorId + ", vendorName=" + vendorName +
               ", contactEmail=" + contactEmail + ", description=" + description +
               ", createdAt=" + createdAt + "]";
    }
}