package com.wallet.transaction.serviceimpl;

import org.springframework.stereotype.Service;

import com.wallet.transaction.entity.Users;
import com.wallet.transaction.repository.UsersRepositoryDeepika;
import com.wallet.transaction.service.UserServiceDeepika;

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
 