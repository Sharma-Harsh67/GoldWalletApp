package com.wallet.app.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.wallet.app.exception.*;
import com.wallet.app.model.Payments;
import com.wallet.app.repository.PaymentsRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PaymentsService {
    
    private static final Logger logger = LoggerFactory.getLogger(PaymentsService.class);
    private final PaymentsRepository paymentsRepository;

    public PaymentsService(PaymentsRepository paymentsRepository) {
        this.paymentsRepository = paymentsRepository;
    }

    /**
     * Get all payments for a specific user
     * @param userId The user ID
     * @return List of payments
     * @throws UserNotFoundException if user has no payments
     * @throws DatabaseConnectionException if database is unreachable
     */
    public List<Payments> getPaymentsByUserId(Integer userId) {
        try {
            logger.info("Fetching payments for user ID: {}", userId);
            
            List<Payments> payments = paymentsRepository.findByUserUserId(userId);
            
            // Check if user has any payments
            if (payments == null || payments.isEmpty()) {
                logger.warn("No payments found for user ID: {}", userId);
                throw new PaymentNotFoundException(userId, "This user has no transaction history");
            }
            
            logger.info("Found {} payments for user ID: {}", payments.size(), userId);
            return payments;
            
        } catch (DataAccessException ex) {
            logger.error("Database error while fetching payments for user {}: {}", userId, ex.getMessage());
            
            // Check if it's a connection issue
            if (ex.getMessage() != null && 
                (ex.getMessage().contains("Communications link failure") || 
                 ex.getMessage().contains("Connection refused"))) {
                throw new DatabaseConnectionException(
                    "Cannot connect to database at 10.108.5.222. Please verify the database server is running.", ex);
            }
            
            throw new DatabaseAccessException("Failed to retrieve payment data", ex);
        }
    }
    
    /**
     * Get a specific payment by ID
     * @param paymentId The payment ID
     * @return Payment object
     * @throws PaymentNotFoundException if payment not found
     */
    public Payments getPaymentById(Integer paymentId) {
        try {
            logger.info("Fetching payment with ID: {}", paymentId);
            
            return paymentsRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException(paymentId));
                
        } catch (DataAccessException ex) {
            logger.error("Database error while fetching payment {}: {}", paymentId, ex.getMessage());
            throw new DatabaseConnectionException("Database connection failed", ex);
        }
    }
}