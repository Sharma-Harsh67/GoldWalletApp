package com.wallet.app.controller;

//import com.wallet.app.dto.AddressResponseJagat;
import com.wallet.app.dto.UserResponseJagat;
import com.wallet.app.service.UserServiceJagat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jagat")
public class UserControllerJagat {

    private final UserServiceJagat userService;

    public UserControllerJagat(UserServiceJagat userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseJagat>> getAllUsers() {
        List<UserResponseJagat> users = userService.getAllUsersSimple();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{id}/address")
    public ResponseEntity<?> getAddressForUser(@PathVariable("id") Integer id) {
        return userService.getAddressForUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/users/address/by-name/{name}")
    public ResponseEntity<?> getAddressByUserName(@PathVariable("name") String name) {
        return userService.getAddressByUserName(name)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
