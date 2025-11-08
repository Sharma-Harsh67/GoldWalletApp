package com.wallet.app.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.SQLRecoverableException;
import java.net.ConnectException;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    // ==================== BUSINESS LOGIC EXCEPTIONS ====================
    
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(
            UserNotFoundException ex, WebRequest request) {
        logger.error("User not found: {}", ex.getMessage());
        
        ErrorResponse error = ErrorResponse.builder()
            .status(HttpStatus.NOT_FOUND.value())
            .error("Not Found")
            .errorCode(ex.getErrorCode())
            .message(ex.getMessage())
            .path(request.getDescription(false).replace("uri=", ""))
            .build();
            
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(VendorNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleVendorNotFoundException(
            VendorNotFoundException ex, WebRequest request) {
        logger.error("Vendor not found: {}", ex.getMessage());
        
        ErrorResponse error = ErrorResponse.builder()
            .status(HttpStatus.NOT_FOUND.value())
            .error("Not Found")
            .errorCode(ex.getErrorCode())
            .message(ex.getMessage())
            .path(request.getDescription(false).replace("uri=", ""))
            .build();
            
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePaymentNotFoundException(
            PaymentNotFoundException ex, WebRequest request) {
        logger.error("Payment not found: {}", ex.getMessage());
        
        ErrorResponse error = ErrorResponse.builder()
            .status(HttpStatus.NOT_FOUND.value())
            .error("Not Found")
            .errorCode(ex.getErrorCode())
            .message(ex.getMessage())
            .path(request.getDescription(false).replace("uri=", ""))
            .build();
            
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientBalanceException(
            InsufficientBalanceException ex, WebRequest request) {
        logger.error("Insufficient balance: {}", ex.getMessage());
        
        ErrorResponse error = ErrorResponse.builder()
            .status(HttpStatus.BAD_REQUEST.value())
            .error("Bad Request")
            .errorCode(ex.getErrorCode())
            .message(ex.getMessage())
            .path(request.getDescription(false).replace("uri=", ""))
            .build();
            
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(InvalidTransactionException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTransactionException(
            InvalidTransactionException ex, WebRequest request) {
        logger.error("Invalid transaction: {}", ex.getMessage());
        
        ErrorResponse error = ErrorResponse.builder()
            .status(HttpStatus.BAD_REQUEST.value())
            .error("Bad Request")
            .errorCode(ex.getErrorCode())
            .message(ex.getMessage())
            .path(request.getDescription(false).replace("uri=", ""))
            .build();
            
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(PaymentProcessingException.class)
    public ResponseEntity<ErrorResponse> handlePaymentProcessingException(
            PaymentProcessingException ex, WebRequest request) {
        logger.error("Payment processing error: {}", ex.getMessage(), ex);
        
        ErrorResponse error = ErrorResponse.builder()
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .error("Internal Server Error")
            .errorCode(ex.getErrorCode())
            .message(ex.getMessage())
            .path(request.getDescription(false).replace("uri=", ""))
            .details("Please contact support if the issue persists")
            .build();
            
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    // ==================== DATABASE EXCEPTIONS ====================
    
    @ExceptionHandler({DatabaseConnectionException.class, CommunicationsException.class})
    public ResponseEntity<ErrorResponse> handleDatabaseConnectionException(
            Exception ex, WebRequest request) {
        logger.error("Database connection error: {}", ex.getMessage(), ex);
        
        String message = "Unable to connect to the database. " +
                        "Please check if the database server is running and accessible.";
        
        ErrorResponse error = ErrorResponse.builder()
            .status(HttpStatus.SERVICE_UNAVAILABLE.value())
            .error("Service Unavailable")
            .errorCode("DB_CONNECTION_ERROR")
            .message(message)
            .path(request.getDescription(false).replace("uri=", ""))
            .details("Database IP: 10.108.5.222 may be unreachable")
            .build();
            
        return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
    }
    
    @ExceptionHandler(SQLTimeoutException.class)
    public ResponseEntity<ErrorResponse> handleSQLTimeoutException(
            SQLTimeoutException ex, WebRequest request) {
        logger.error("Database timeout: {}", ex.getMessage(), ex);
        
        ErrorResponse error = ErrorResponse.builder()
            .status(HttpStatus.REQUEST_TIMEOUT.value())
            .error("Request Timeout")
            .errorCode("DB_TIMEOUT")
            .message("Database operation timed out. The server might be slow or overloaded.")
            .path(request.getDescription(false).replace("uri=", ""))
            .details("Please try again in a few moments")
            .build();
            
        return new ResponseEntity<>(error, HttpStatus.REQUEST_TIMEOUT);
    }
    
    @ExceptionHandler(SQLRecoverableException.class)
    public ResponseEntity<ErrorResponse> handleSQLRecoverableException(
            SQLRecoverableException ex, WebRequest request) {
        logger.error("Recoverable SQL error: {}", ex.getMessage(), ex);
        
        ErrorResponse error = ErrorResponse.builder()
            .status(HttpStatus.SERVICE_UNAVAILABLE.value())
            .error("Service Unavailable")
            .errorCode("DB_CONNECTION_ERROR")
            .message("Temporary database connection issue. Please retry.")
            .path(request.getDescription(false).replace("uri=", ""))
            .build();
            
        return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
    }
    
    @ExceptionHandler(InvalidDatabaseConfigurationException.class)
    public ResponseEntity<ErrorResponse> handleInvalidDatabaseConfiguration(
            InvalidDatabaseConfigurationException ex, WebRequest request) {
        logger.error("Invalid database configuration: {}", ex.getMessage());
        
        ErrorResponse error = ErrorResponse.builder()
            .status(HttpStatus.SERVICE_UNAVAILABLE.value())
            .error("Service Unavailable")
            .errorCode(ex.getErrorCode())
            .message(ex.getMessage())
            .path(request.getDescription(false).replace("uri=", ""))
            .details("Contact administrator to verify database configuration")
            .build();
            
        return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
    }
    
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorResponse> handleDataAccessException(
            DataAccessException ex, WebRequest request) {
        logger.error("Data access error: {}", ex.getMessage(), ex);
        
        String message = "Database access error occurred.";
        String details = null;
        
        // Check for specific database issues
        if (ex.getMessage() != null) {
            if (ex.getMessage().contains("Communications link failure") || 
                ex.getMessage().contains("Connection refused")) {
                message = "Cannot connect to remote database at 10.108.5.222";
                details = "Verify the database server is running and IP is correct";
            } else if (ex.getMessage().contains("too many connections")) {
                message = "Database has too many active connections";
                details = "System is overloaded. Please try again shortly";
            }
        }
        
        ErrorResponse error = ErrorResponse.builder()
            .status(HttpStatus.SERVICE_UNAVAILABLE.value())
            .error("Service Unavailable")
            .errorCode("DB_ACCESS_ERROR")
            .message(message)
            .path(request.getDescription(false).replace("uri=", ""))
            .details(details)
            .build();
            
        return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
    }
    
    // ==================== SYSTEM RESOURCE EXCEPTIONS ====================
    
    @ExceptionHandler(SystemOverloadException.class)
    public ResponseEntity<ErrorResponse> handleSystemOverloadException(
            SystemOverloadException ex, WebRequest request) {
        logger.error("System overload: {}", ex.getMessage());
        
        ErrorResponse error = ErrorResponse.builder()
            .status(HttpStatus.SERVICE_UNAVAILABLE.value())
            .error("Service Unavailable")
            .errorCode(ex.getErrorCode())
            .message(ex.getMessage())
            .path(request.getDescription(false).replace("uri=", ""))
            .details("Too many requests. Please try again later")
            .build();
            
        return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
    }
    
    @ExceptionHandler(TooManyConnectionsException.class)
    public ResponseEntity<ErrorResponse> handleTooManyConnectionsException(
            TooManyConnectionsException ex, WebRequest request) {
        logger.error("Too many connections: {}", ex.getMessage());
        
        ErrorResponse error = ErrorResponse.builder()
            .status(HttpStatus.SERVICE_UNAVAILABLE.value())
            .error("Service Unavailable")
            .errorCode(ex.getErrorCode())
            .message(ex.getMessage())
            .path(request.getDescription(false).replace("uri=", ""))
            .details("System is experiencing high load. Retry in a few seconds")
            .build();
            
        return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
    }
    
    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<ErrorResponse> handleConnectException(
            ConnectException ex, WebRequest request) {
        logger.error("Connection refused: {}", ex.getMessage(), ex);
        
        ErrorResponse error = ErrorResponse.builder()
            .status(HttpStatus.SERVICE_UNAVAILABLE.value())
            .error("Service Unavailable")
            .errorCode("NETWORK_ERROR")
            .message("Cannot connect to remote database. Connection refused.")
            .path(request.getDescription(false).replace("uri=", ""))
            .details("Database server at 10.108.5.222 may be down or IP changed")
            .build();
            
        return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
    }
    
    // ==================== GENERIC EXCEPTIONS ====================
    
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ErrorResponse> handleSQLException(
            SQLException ex, WebRequest request) {
        logger.error("SQL error: {}", ex.getMessage(), ex);
        
        ErrorResponse error = ErrorResponse.builder()
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .error("Internal Server Error")
            .errorCode("SQL_ERROR")
            .message("Database operation failed")
            .path(request.getDescription(false).replace("uri=", ""))
            .details("SQL Error Code: " + ex.getErrorCode())
            .build();
            
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex, WebRequest request) {
        logger.error("Unexpected error: {}", ex.getMessage(), ex);
        
        ErrorResponse error = ErrorResponse.builder()
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .error("Internal Server Error")
            .errorCode("INTERNAL_ERROR")
            .message("An unexpected error occurred")
            .path(request.getDescription(false).replace("uri=", ""))
            .details("Please contact support if the issue persists")
            .build();
            
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
