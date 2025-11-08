package com.wallet.app.repository;

import com.wallet.app.dto.BranchSummaryDTO;
import com.wallet.app.model.VendorBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<VendorBranch, Long> {

    /**
     * Get summary of all branches with their gold quantities
     */
    @Query("SELECT new com.wallet.app.dto.BranchSummaryDTO(vb.vendorId, vb.branchId, vb.branch_name, vb.quantity) " +
           "FROM VendorBranch vb")
    List<BranchSummaryDTO> getBranchSummary();

    /**
     * Get branch summary for a specific vendor
     */
    @Query("SELECT new com.wallet.app.dto.BranchSummaryDTO(vb.vendorId, vb.branchId, vb.branch_name, vb.quantity) " +
           "FROM VendorBranch vb WHERE vb.vendorId = :vendorId")
    List<BranchSummaryDTO> getBranchSummaryByVendorId(@Param("vendorId") Long vendorId);
}