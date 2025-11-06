package com.wallet.transaction.controller;

import org.springframework.web.bind.annotation.*;

import com.wallet.transaction.entity.Users;
import com.wallet.transaction.service.UserServiceDeepika;

import java.util.List;

@RestController

@RequestMapping("/api/users")

public class UserControllerDeepika {

    

    public UserControllerDeepika(UserServiceDeepika userService) {
		super();
		this.userService = userService;
	}

	private UserServiceDeepika userService;

    // MASTER TABLE API

    @GetMapping

    public List<Users> getAllUsers() {

        return userService.getAllUsers();

    }

  
}
 