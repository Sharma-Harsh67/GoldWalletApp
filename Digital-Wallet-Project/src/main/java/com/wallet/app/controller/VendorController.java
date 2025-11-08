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

    /**
     * Get all vendors
     * @return List of all vendors
     */
    @GetMapping
    public ResponseEntity<List<Vendor>> getAllVendors() {
        return ResponseEntity.ok(vendorService.getAllVendors());
    }

    /**
     * Get vendor by ID
     * @param id Vendor ID
     * @return Vendor details
     */
    @GetMapping("/{id}")
    public ResponseEntity<Vendor> getVendorById(@PathVariable Long id) {
        Vendor vendor = vendorService.getVendorById(id);
        if (vendor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vendor);
    }

    /**
     * Get transactions by vendor ID
     * @param id Vendor ID
     * @return List of transactions
     */
    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<VendorTransactionDTO>> getTransactionsByVendor(@PathVariable Long id) {
        return ResponseEntity.ok(vendorTransactionService.getTransactionsByVendorId(id));
    }

    /**
     * Get transactions by vendor name
     * @param vendorName Vendor name
     * @return List of transactions
     */
    @GetMapping("/transactions")
    public ResponseEntity<List<VendorTransactionDTO>> getTransactionsByVendorName(@RequestParam String vendorName) {
        return ResponseEntity.ok(vendorTransactionService.getTransactionsByVendorName(vendorName));
    }

    /**
     * Get user transactions by user name
     * @param userName User name
     * @return List of user transactions
     */
    @GetMapping("/user-transactions")
    public ResponseEntity<List<UserTransactionDTO>> getUserTransactions(@RequestParam String userName) {
        return ResponseEntity.ok(vendorTransactionService.getTransactionsByUserName(userName));
    }

    /**
     * Get summary of all branches with their gold quantities
     * @return List of all branch summaries
     */
    @GetMapping("/branch-summary")
    public ResponseEntity<List<BranchSummaryDTO>> getBranchSummary() {
        return ResponseEntity.ok(vendorService.getBranchSummary());
    }

    /**
     * Get branch summary for a specific vendor
     * @param vendorId Vendor ID (query parameter)
     * @return List of branches with vendor_id, branch_id, and branch_name
     * 
     * Example usage: GET /api/vendors/branch-summary/by-vendor?vendorId=1
     */
    @GetMapping("/branch-summary/by-vendor")
    public ResponseEntity<List<BranchSummaryDTO>> getBranchSummaryByVendorId(@RequestParam Long vendorId) {
        return ResponseEntity.ok(vendorService.getBranchSummaryByVendorId(vendorId));
    }
}