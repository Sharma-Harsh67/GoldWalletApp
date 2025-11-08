package com.wallet.app.exception;

import java.net.URL;

public class InvalidDatabaseConfigurationException extends WalletApplicationException {

    private static final long serialVersionUID = 1L;

    public InvalidDatabaseConfigurationException(String message) {
        super("Invalid database configuration: " + message, "DB_CONFIG_ERROR");
    }

    public InvalidDatabaseConfigurationException(String dbUrl, boolean isUrl) {
        super("Cannot connect to database at: " + dbUrl + 
              ". Please check if the IP address is correct and the database server is running.", 
              "DB_CONFIG_ERROR");
    }


}
