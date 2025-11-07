package com.wallet.app.controller;

//import com.wallet.app.dto.AddressResponseJagat;
import com.wallet.app.dto.UserDTOJagat;
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

    /**
     * ✅ Returns list of users (user_id, name, email, address_id)
     */
    @GetMapping("/users")
    public ResponseEntity<List<UserDTOJagat>> getAllUsers() {
        List<UserDTOJagat> users = userService.getAllUsersSimple();
        return ResponseEntity.ok(users);
    }

    /**
     * ✅ Returns the address for a specific user id.
     * Example: GET /api/jagat/users/1/address
     */
    @GetMapping("/users/{id}/address")
    public ResponseEntity<?> getAddressForUser(@PathVariable("id") Integer id) {
        return userService.getAddressForUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * ✅ NEW ENDPOINT: Get address by user name
     * Example: GET /api/jagat/users/address/by-name/Alice Jagat
     */
    @GetMapping("/users/address/by-name/{name}")
    public ResponseEntity<?> getAddressByUserName(@PathVariable("name") String name) {
        return userService.getAddressByUserName(name)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
