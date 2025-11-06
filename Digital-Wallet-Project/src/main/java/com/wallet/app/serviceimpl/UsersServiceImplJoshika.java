package com.wallet.serviceImpl;

import com.wallet.app.model.Users;
import com.wallet.repository.UsersRepositoryJoshika;
import com.wallet.service.UsersServiceJoshika;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImplJoshika implements UsersServiceJoshika {


public UsersServiceImplJoshika(UsersRepositoryJoshika userRepository) {
		super();
		this.userRepository = userRepository;
	}

private UsersRepositoryJoshika userRepository;

@Override
public List<Users> getAllUsers() {
return userRepository.findAll();
}




}

