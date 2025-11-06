package com.wallet.app.service;

import com.wallet.app.dto.VendorTransactionDTO;
import com.wallet.app.dto.UserTransactionDTO;
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

    @Transactional(readOnly = true)
    public List<VendorTransactionDTO> getTransactionsByVendorId(Long vendorId) {
        List<VendorBranch> branches = branchRepository.findByVendorVendorId(vendorId);
        List<Long> branchIds = branches.stream()
                .map(VendorBranch::getBranchId)
                .collect(Collectors.toList());
        
        if (branchIds.isEmpty()) {
            return new ArrayList<>();
        }
        
        List<PhysicalGoldTransactions> transactions = transactionRepository.findByBranchBranchIdIn(branchIds);
        return mapToVendorTransactionDTO(transactions);
    }

    @Transactional(readOnly = true)
    public List<VendorTransactionDTO> getTransactionsByVendorName(String vendorName) {
        Vendor vendor = vendorRepository.findByVendorName(vendorName);
        if (vendor == null) {
            return new ArrayList<>();
        }
        return getTransactionsByVendorId(vendor.getVendorId());
    }

    @Transactional(readOnly = true)
    public List<UserTransactionDTO> getTransactionsByUserName(String userName) {
        List<PhysicalGoldTransactions> transactions = transactionRepository.findByUserName(userName);
        return mapToUserTransactionDTO(transactions);
    }

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