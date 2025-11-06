package com.wallet.app.controller;

import com.wallet.app.model.Users;
import com.wallet.app.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users/kiranmayee")  // ✅ Unique path
public class UsersControllerKiranmayee {

    @Autowired
    private UsersService usersService;

    // This will now be accessible at: GET /api/users/kiranmayee
    @GetMapping
    public List<String> getAllUserNamesandEmails() {
        return usersService.getAllUsers().stream()
            .map(user -> "Name: " + user.getName() + ", Email: " + user.getEmail())
            .collect(Collectors.toList());
    }

    // Add other methods if needed with unique paths
    @GetMapping("/names")
    public List<String> getAllUserNames() {
        return usersService.getAllUsers().stream()
            .map(Users::getName)
            .collect(Collectors.toList());
    }

    @GetMapping("/emails")
    public List<String> getAllUserEmails() {
        return usersService.getAllUsers().stream()
            .map(Users::getEmail)
            .collect(Collectors.toList());
    }
}