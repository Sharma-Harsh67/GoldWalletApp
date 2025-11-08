package com.wallet.app.exception;

class VirtualGoldHoldingNotFoundException extends WalletApplicationException {
    public VirtualGoldHoldingNotFoundException(Integer holdingId) {
        super("Virtual gold holding not found with ID: " + holdingId, "HOLDING_NOT_FOUND");
    }
    
    public VirtualGoldHoldingNotFoundException(Integer userId, String message) {
        super("No virtual gold holdings found for user ID: " + userId + ". " + message, 
            "HOLDING_NOT_FOUND");
    }
}
