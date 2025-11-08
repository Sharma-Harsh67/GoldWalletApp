package com.wallet.app.controller;

import com.wallet.app.model.Payments;
import com.wallet.app.model.Users;
import com.wallet.app.service.PaymentsService;
import com.wallet.app.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    
    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);
    private final UsersService usersService;
    private final PaymentsService paymentsService;
    
    public UsersController(UsersService usersService, PaymentsService paymentsService) {
        this.usersService = usersService;
        this.paymentsService = paymentsService;
    }

    /**
     * Get all users
     * GET /api/users
     */
    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        logger.info("GET /api/users - Fetching all users");
        List<Users> users = usersService.getAllUsers();
        logger.info("Retrieved {} users", users.size());
        return ResponseEntity.ok(users);
    }

    /**
     * Get user by ID
     * GET /api/users/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Integer id) {
        logger.info("GET /api/users/{} - Fetching user", id);
        Users user = usersService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    
    /**
     * Get all payments/transactions for a specific user
     * GET /api/users/{id}/payments
     * 
     * @throws PaymentNotFoundException if user has no payments
     * @throws DatabaseConnectionException if database is unreachable
     */
    @GetMapping("/{id}/payments")
    public ResponseEntity<List<Payments>> getPaymentsByUser(@PathVariable Integer id) {
        logger.info("GET /api/users/{}/payments - Fetching user payments", id);
        
        // This will throw PaymentNotFoundException if no payments found
        // GlobalExceptionHandler will catch it and return proper error response
        List<Payments> payments = paymentsService.getPaymentsByUserId(id);
        
        logger.info("Retrieved {} payments for user {}", payments.size(), id);
        return ResponseEntity.ok(payments);
    }
}