package com.wallet.app.exception;

public class UserNotFoundException extends WalletApplicationException {
    public UserNotFoundException(Integer userId) {
        super("User not found with ID: " + userId, "USER_NOT_FOUND");
    }
    
    public UserNotFoundException(String username) {
        super("User not found with username: " + username, "USER_NOT_FOUND");
    }
}
