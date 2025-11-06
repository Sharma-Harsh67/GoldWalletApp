package com.wallet.app.controller;

import com.wallet.app.dto.BranchSummaryDTO;
import com.wallet.app.model.PhysicalGoldTransaction;
import com.wallet.app.model.Vendor;
import com.wallet.app.service.VendorService;
import com.wallet.app.service.VendorTransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
@CrossOrigin
public class VendorController {
    private final VendorService vendorService;
    private final VendorTransactionService vendorTransactionService;

    public VendorController(VendorService vendorService, VendorTransactionService vendorTransactionService) {
        this.vendorService = vendorService;
        this.vendorTransactionService = vendorTransactionService;
    }

    @GetMapping
    public List<Vendor> getAllVendors() {
        return vendorService.getAllVendors();
    }

    @GetMapping("/{id}")
    public Vendor getVendorById(@PathVariable Long id) {
        return vendorService.getVendorById(id);
    }

    @GetMapping("/{id}/transactions")
    public List<PhysicalGoldTransaction> getTransactionsByVendor(@PathVariable Long id) {
        return vendorTransactionService.getTransactionsByVendorId(id);
    }

    @GetMapping("/branch-summary")
    public List<BranchSummaryDTO> getBranchSummary() {
        return vendorService.getBranchSummary();
    }
}