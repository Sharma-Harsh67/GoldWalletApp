package com.wallet.app.service;

import com.wallet.app.dto.VendorTransactionDTO;
import com.wallet.app.dto.UserTransactionDTO;
import com.wallet.app.exception.UserNotFoundException;
import com.wallet.app.exception.VendorNotFoundException;
import com.wallet.app.model.PhysicalGoldTransactions;
import com.wallet.app.model.Vendor;
import com.wallet.app.model.VendorBranch;
import com.wallet.app.repository.PhysicalGoldTransactionRepository;
import com.wallet.app.repository.VendorBranchRepository;
import com.wallet.app.repository.VendorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorTransactionService {

    private final VendorBranchRepository branchRepository;
    private final PhysicalGoldTransactionRepository transactionRepository;
    private final VendorRepository vendorRepository;

    public VendorTransactionService(VendorBranchRepository branchRepository, 
                                   PhysicalGoldTransactionRepository transactionRepository,
                                   VendorRepository vendorRepository) {
        this.branchRepository = branchRepository;
        this.transactionRepository = transactionRepository;
        this.vendorRepository = vendorRepository;
    }

    // ==================== Vendor Transactions ====================
    @Transactional(readOnly = true)
    public List<VendorTransactionDTO> getTransactionsByVendorId(Long vendorId) {
        List<VendorBranch> branches = branchRepository.findByVendorVendorId(vendorId);
        if (branches == null || branches.isEmpty()) {
            throw new VendorNotFoundException(vendorId);
        }

        List<Long> branchIds = branches.stream()
                .map(VendorBranch::getBranchId)
                .collect(Collectors.toList());

        List<PhysicalGoldTransactions> transactions = transactionRepository.findByBranchBranchIdIn(branchIds);
        if (transactions == null || transactions.isEmpty()) {
            throw new VendorNotFoundException(vendorId);
        }

        return mapToVendorTransactionDTO(transactions);
    }

    @Transactional(readOnly = true)
    public List<VendorTransactionDTO> getTransactionsByVendorName(String vendorName) {
        Vendor vendor = vendorRepository.findByVendorName(vendorName);
        if (vendor == null) {
            throw new VendorNotFoundException(vendorName);
        }
        return getTransactionsByVendorId(vendor.getVendorId());
    }

    // ==================== User Transactions ====================
    @Transactional(readOnly = true)
    public List<UserTransactionDTO> getTransactionsByUserName(String userName) {
        List<PhysicalGoldTransactions> transactions = transactionRepository.findByUserName(userName);

        if (transactions == null || transactions.isEmpty()) {
            throw new UserNotFoundException(userName);
        }

        return mapToUserTransactionDTO(transactions);
    }

    // ==================== Mapping Methods ====================
    private List<VendorTransactionDTO> mapToVendorTransactionDTO(List<PhysicalGoldTransactions> transactions) {
        return transactions.stream()
                .map(t -> new VendorTransactionDTO(
                        t.getTransactionId(),
                        t.getUser().getUserId(),
                        t.getBranch().getBranchId(),
                        t.getDeliveryAddressId(),
                        t.getQuantity(),
                        t.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }

    private List<UserTransactionDTO> mapToUserTransactionDTO(List<PhysicalGoldTransactions> transactions) {
        return transactions.stream()
                .map(t -> new UserTransactionDTO(
                        t.getTransactionId(),
                        t.getUser().getUserId(),
                        t.getBranch().getBranchId(),
                        t.getDeliveryAddressId(),
                        t.getQuantity(),
                        t.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }
}
