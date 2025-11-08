package com.wallet.app.exception;

//==================== SYSTEM RESOURCE EXCEPTIONS ====================

public class SystemOverloadExcepti extends WalletApplicationException {
 public SystemOverloadExcepti(String message) {
     super("System is currently overloaded: " + message, "SYSTEM_OVERLOAD");
 }
}
 