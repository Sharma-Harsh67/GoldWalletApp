package com.wallet.app.controller;

import com.wallet.app.dto.BranchSummaryDTO;
import com.wallet.app.dto.VendorTransactionDTO;
import com.wallet.app.dto.UserTransactionDTO;
import com.wallet.app.model.Vendor;
import com.wallet.app.service.VendorService;
import com.wallet.app.service.VendorTransactionService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Vendor>> getAllVendors() {
        return ResponseEntity.ok(vendorService.getAllVendors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendor> getVendorById(@PathVariable Long id) {
        Vendor vendor = vendorService.getVendorById(id);
        if (vendor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vendor);
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<VendorTransactionDTO>> getTransactionsByVendor(@PathVariable Long id) {
        return ResponseEntity.ok(vendorTransactionService.getTransactionsByVendorId(id));
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<VendorTransactionDTO>> getTransactionsByVendorName(@RequestParam String vendorName) {
        return ResponseEntity.ok(vendorTransactionService.getTransactionsByVendorName(vendorName));
    }

    @GetMapping("/user-transactions")
    public ResponseEntity<List<UserTransactionDTO>> getUserTransactions(@RequestParam String userName) {
        return ResponseEntity.ok(vendorTransactionService.getTransactionsByUserName(userName));
    }

    @GetMapping("/branch-summary")
    public ResponseEntity<List<BranchSummaryDTO>> getBranchSummary() {
        return ResponseEntity.ok(vendorService.getBranchSummary());
    }
}