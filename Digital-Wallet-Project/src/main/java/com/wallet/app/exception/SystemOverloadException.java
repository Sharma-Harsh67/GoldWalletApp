package com.wallet.app.exception;


//==================== SYSTEM RESOURCE EXCEPTIONS ====================

public class SystemOverloadException extends WalletApplicationException {
 public SystemOverloadException(String message) {
     super("System is currently overloaded: " + message, "SYSTEM_OVERLOAD");
 }
}

