package com.wallet.app.repository;

import com.wallet.app.dto.BranchSummaryDTO;
import com.wallet.app.model.VendorBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<VendorBranch, Long> {

    @Query("SELECT new com.wallet.app.dto.BranchSummaryDTO(vb.branchName, vb.quantity) FROM VendorBranch vb")
    List<BranchSummaryDTO> getBranchSummary();
}