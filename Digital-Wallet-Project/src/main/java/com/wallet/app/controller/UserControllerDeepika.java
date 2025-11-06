package com.wallet.app.controller;

import com.wallet.app.model.Users;
import com.wallet.app.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/deepika")  // ✅ Unique path
public class UserControllerDeepika {

    @Autowired
    private UsersService usersService;

    @GetMapping
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Integer id) {
        return usersService.getUserById(id);
    }

//    @PostMapping
//    public Users createUser(@RequestBody Users user) {
//        return usersService.createUser(user);
//    }
//
//    @PutMapping("/{id}")
//    public Users updateUser(@PathVariable Integer id, @RequestBody Users user) {
//        return usersService.updateUser(id, user);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteUser(@PathVariable Integer id) {
//        usersService.deleteUser(id);
//    }
}