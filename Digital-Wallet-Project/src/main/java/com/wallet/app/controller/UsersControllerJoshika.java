package com.wallet.controller;



import java.util.List;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.wallet.app.model.Users;
import com.wallet.service.UsersServiceJoshika;

@RestController
@RequestMapping("/api/users")
public class UsersControllerJoshika {


public UsersControllerJoshika(UsersServiceJoshika userService) {
		super();
		this.userService = userService;
	}

private UsersServiceJoshika userService;

@GetMapping
public List<Users> getAllUsers() {
return userService.getAllUsers();
}

}


