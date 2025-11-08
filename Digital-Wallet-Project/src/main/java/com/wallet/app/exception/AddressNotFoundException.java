package com.wallet.app.exception;

class AddressNotFoundException extends WalletApplicationException {
    public AddressNotFoundException(Integer addressId) {
        super("Address not found with ID: " + addressId, "ADDRESS_NOT_FOUND");
    }
}
