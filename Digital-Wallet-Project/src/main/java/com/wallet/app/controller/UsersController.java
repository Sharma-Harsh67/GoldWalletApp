package com.wallet.app.controller;

import com.wallet.app.model.Payments;
import com.wallet.app.model.Users;
import com.wallet.app.service.PaymentsService;
import com.wallet.app.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")  // ✅ Main path
public class UsersController {

    private final UsersService usersService ;
    private final PaymentsService paymentsService ;
    
    public UsersController(UsersService usersService, PaymentsService paymentsService) {
        this.usersService = usersService;
        this.paymentsService = paymentsService;
    }

    @GetMapping
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Integer id) {
        return usersService.getUserById(id);
    }
    
    @GetMapping("/{id}/payments")
    public ResponseEntity<List<Payments>> getPaymentsByUser(@PathVariable Integer id) {
        return ResponseEntity.ok(paymentsService.getPaymentsByUserId(id));
    }
    
}