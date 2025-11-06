package com.wallet.app.service;

import com.wallet.app.dto.BranchSummaryDTO;
import com.wallet.app.model.Vendor;
import com.wallet.app.repository.BranchRepository;
import com.wallet.app.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {
    private final BranchRepository branchRepository;
    private final VendorRepository vendorRepository;

    public VendorService(BranchRepository branchRepository, VendorRepository vendorRepository) {
        this.branchRepository = branchRepository;
        this.vendorRepository = vendorRepository;
    }

    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    public Vendor getVendorById(Long id) {
        return vendorRepository.findById(id).orElse(null);
    }

    public List<BranchSummaryDTO> getBranchSummary() {
        return branchRepository.getBranchSummary();
    }
}
