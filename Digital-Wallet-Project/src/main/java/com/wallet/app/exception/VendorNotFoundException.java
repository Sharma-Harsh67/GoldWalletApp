package com.wallet.app.exception;

public class VendorNotFoundException extends WalletApplicationException {
    public VendorNotFoundException(Long vendorId) {
        super("Vendor not found with ID: " + vendorId, "VENDOR_NOT_FOUND");
    }
    
    public VendorNotFoundException(String vendorName) {
        super("Vendor not found with name: " + vendorName, "VENDOR_NOT_FOUND");
    }
}
