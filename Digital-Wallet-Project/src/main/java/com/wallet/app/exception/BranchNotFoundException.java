package com.wallet.app.exception;

class BranchNotFoundException extends WalletApplicationException {
    public BranchNotFoundException(Integer branchId) {
        super("Branch not found with ID: " + branchId, "BRANCH_NOT_FOUND");
    }
}
