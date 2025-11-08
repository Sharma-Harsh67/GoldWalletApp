package com.wallet.app.service;

import com.wallet.app.dto.BranchSummaryDTO;
import com.wallet.app.exception.*;
import com.wallet.app.model.Vendor;
import com.wallet.app.repository.BranchRepository;
import com.wallet.app.repository.VendorRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class VendorService {
    
    private static final Logger logger = LoggerFactory.getLogger(VendorService.class);
    private final BranchRepository branchRepository;
    private final VendorRepository vendorRepository;

    public VendorService(BranchRepository branchRepository, VendorRepository vendorRepository) {
        this.branchRepository = branchRepository;
        this.vendorRepository = vendorRepository;
    }

    /**
     * Get all vendors from the system
     * @return List of all vendors
     * @throws DatabaseConnectionException if database is unreachable
     */
    @Transactional(readOnly = true)
    public List<Vendor> getAllVendors() {
        try {
            logger.info("Fetching all vendors");
            
            List<Vendor> vendors = vendorRepository.findAll();
            
            if (vendors.isEmpty()) {
                logger.warn("No vendors found in the system");
            } else {
                logger.info("Found {} vendors", vendors.size());
            }
            
            return vendors;
            
        } catch (DataAccessException ex) {
            logger.error("Database error while fetching vendors: {}", ex.getMessage());
            handleDatabaseException(ex);
            throw ex; // Should not reach here
        }
    }

    /**
     * Get vendor by ID
     * @param id Vendor ID
     * @return Vendor object
     * @throws VendorNotFoundException if vendor doesn't exist
     * @throws DatabaseConnectionException if database is unreachable
     */
    @Transactional(readOnly = true)
    public Vendor getVendorById(Long id) {
        try {
            logger.info("Fetching vendor with ID: {}", id);
            
            Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Vendor not found with ID: {}", id);
                    return new VendorNotFoundException(id);
                });
            
            logger.info("Found vendor: {}", vendor.getVendorName());
            return vendor;
            
        } catch (VendorNotFoundException ex) {
            throw ex; // Re-throw business exception
        } catch (DataAccessException ex) {
            logger.error("Database error while fetching vendor {}: {}", id, ex.getMessage());
            handleDatabaseException(ex);
            throw ex; // Should not reach here
        }
    }

    /**
     * Get summary of all branches with their gold quantities
     * @return List of branch summaries
     * @throws DatabaseConnectionException if database is unreachable
     */
    @Transactional(readOnly = true)
    public List<BranchSummaryDTO> getBranchSummary() {
        try {
            logger.info("Fetching branch summary");
            
            List<BranchSummaryDTO> summary = branchRepository.getBranchSummary();
            
            if (summary.isEmpty()) {
                logger.warn("No branch data available");
            } else {
                logger.info("Retrieved summary for {} branches", summary.size());
            }
            
            return summary;
            
        } catch (DataAccessException ex) {
            logger.error("Database error while fetching branch summary: {}", ex.getMessage());
            handleDatabaseException(ex);
            throw ex; // Should not reach here
        }
    }
    
    /**
     * Get branch summary for a specific vendor
     * @param vendorId Vendor ID
     * @return List of branch summaries for the vendor
     * @throws VendorNotFoundException if vendor doesn't exist
     * @throws DatabaseConnectionException if database is unreachable
     */
    @Transactional(readOnly = true)
    public List<BranchSummaryDTO> getBranchSummaryByVendorId(Long vendorId) {
        try {
            logger.info("Fetching branch summary for vendor ID: {}", vendorId);
            
            // First verify the vendor exists
            if (!vendorRepository.existsById(vendorId)) {
                logger.warn("Vendor not found with ID: {}", vendorId);
                throw new VendorNotFoundException(vendorId);
            }
            
            List<BranchSummaryDTO> summary = branchRepository.getBranchSummaryByVendorId(vendorId);
            
            if (summary.isEmpty()) {
                logger.warn("No branches found for vendor ID: {}", vendorId);
            } else {
                logger.info("Retrieved {} branches for vendor ID: {}", summary.size(), vendorId);
            }
            
            return summary;
            
        } catch (VendorNotFoundException ex) {
            throw ex; // Re-throw business exception
        } catch (DataAccessException ex) {
            logger.error("Database error while fetching branch summary for vendor {}: {}", vendorId, ex.getMessage());
            handleDatabaseException(ex);
            throw ex; // Should not reach here
        }
    }
    
    /**
     * Helper method to handle database exceptions
     */
    private void handleDatabaseException(DataAccessException ex) {
        String errorMessage = ex.getMessage();
        
        if (errorMessage != null) {
            if (errorMessage.contains("Communications link failure") || 
                errorMessage.contains("Connection refused")) {
                throw new DatabaseConnectionException(
                    "Cannot connect to remote database at 10.108.5.222. " +
                    "Please verify the IP address and ensure the database server is running.", ex);
            } else if (errorMessage.contains("too many connections")) {
                throw new TooManyConnectionsException();
            } else if (errorMessage.contains("timeout")) {
                throw new DatabaseTimeoutException("Vendor data retrieval", ex);
            }
        }
        
        throw new DatabaseAccessException("Failed to access vendor data", ex);
    }
}