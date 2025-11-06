package com.wallet.app.serviceimpl;

import org.springframework.stereotype.Service;

import com.wallet.app.model.Users;
import com.wallet.app.repository.UsersRepositoryDeepika;
import com.wallet.app.service.UserServiceDeepika;

import java.util.List;

@Service

public class UserServiceImplDeepika implements UserServiceDeepika {

    public UserServiceImplDeepika(UsersRepositoryDeepika usersRepository) {
		super();
		this.usersRepository = usersRepository;
	}

	private UsersRepositoryDeepika usersRepository;

    @Override

    public List<Users> getAllUsers() {

        return usersRepository.findAll();

    }



}
 