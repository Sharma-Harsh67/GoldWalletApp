package com.digitalgoldwallet.controller;

import com.digitalgoldwallet.entity.Users;
import com.digitalgoldwallet.dto.TransactionQuantityDTOKiranmayee;
import com.digitalgoldwallet.dto.UsersDTOKiranmayee;
import com.digitalgoldwallet.service.UsersServiceKiranmayee;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersControllerKiranmayee{

  
    private UsersServiceKiranmayee userService;
    public UsersControllerKiranmayee(UsersServiceKiranmayee userService) {
        this.userService = userService;
    }
    @GetMapping
    public List<UsersDTOKiranmayee> getAllUserNamesandEmails() {
        return userService.getAllUserDTOs();
    }
  
}
