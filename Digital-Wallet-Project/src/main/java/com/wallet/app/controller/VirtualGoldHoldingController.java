package com.wallet.app.controller;
 
import com.wallet.app.model.Users;

import com.wallet.app.dto.VirtualGoldHoldingDTO;

import com.wallet.app.service.UsersService;

import com.wallet.app.service.VirtualGoldHoldingsService;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
 
import java.util.List;
 
@RestController

@RequestMapping("/api/user-gold")  // ✅ Unified base path

@CrossOrigin

public class VirtualGoldHoldingController {
 
    private  UsersService usersService;

    private  VirtualGoldHoldingsService goldService;
 
    public VirtualGoldHoldingController(UsersService usersService, VirtualGoldHoldingsService goldService) {

        this.usersService = usersService;

        this.goldService = goldService;

    }
 
    // -------------------- USER ENDPOINTS --------------------
 
    @GetMapping("/users")

    public ResponseEntity<List<Users>> getAllUsers() {

        return ResponseEntity.ok(usersService.getAllUsers());

    }
 
    @GetMapping("/users/{id}")

    public ResponseEntity<Users> getUserById(@PathVariable Integer id) {

        Users user = usersService.getUserById(id);

        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();

    }
 
    // -------------------- VIRTUAL GOLD ENDPOINTS --------------------
 
    @GetMapping("/holdings-by-name")

    public ResponseEntity<List<VirtualGoldHoldingDTO>> getHoldingsByName(@RequestParam String name) {

        return ResponseEntity.ok(goldService.getHoldingsByName(name));

    }

}
 