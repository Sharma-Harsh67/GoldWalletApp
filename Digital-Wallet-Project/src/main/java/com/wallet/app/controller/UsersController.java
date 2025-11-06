package com.wallet.app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.app.model.Payments;
import com.wallet.app.model.Users;
import com.wallet.app.service.PaymentsService;
import com.wallet.app.service.UsersService;



@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;
    private final PaymentsService paymentsService;

    public UsersController(UsersService usersService, PaymentsService paymentsService) {
        this.usersService = usersService;
        this.paymentsService = paymentsService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(usersService.getUserById(id));
    }

    @GetMapping("/{id}/payments")
    public ResponseEntity<List<Payments>> getPaymentsByUser(@PathVariable Integer id) {
        return ResponseEntity.ok(paymentsService.getPaymentsByUserId(id));
    }
}