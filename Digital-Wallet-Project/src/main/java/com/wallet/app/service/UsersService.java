package com.wallet.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wallet.app.model.Users;
import com.wallet.app.repository.UsersRepository;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    
    public Users getUserById(Integer id) {
        return usersRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}