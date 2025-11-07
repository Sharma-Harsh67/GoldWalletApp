package com.wallet.app.service;



import com.wallet.app.dto.UsersDTOKiranmayee;



import com.wallet.app.repository.UsersRepositoryKiranmayee;

import com.wallet.app.repository.PhysicalGoldTransactionsRepositoryKiranmayee;

import org.springframework.stereotype.Service;
 
import java.util.List;

import java.util.stream.Collectors;
 
@Service
 
public class UsersServiceKiranmayee {
 
   

    private UsersRepositoryKiranmayee usersRepository;
 
   

    private PhysicalGoldTransactionsRepositoryKiranmayee transactionsRepository;

    public UsersServiceKiranmayee(UsersRepositoryKiranmayee usersRepository,

            PhysicalGoldTransactionsRepositoryKiranmayee transactionsRepository) {

this.usersRepository = usersRepository;

this.transactionsRepository = transactionsRepository;

    }
 
    

    public List<UsersDTOKiranmayee> getAllUserDTOs() {

        return usersRepository.findAll().stream()

            .map(user -> new UsersDTOKiranmayee(user.getUserId(), user.getName(), user.getEmail(),user.getAddressId(),user.getBalance(),user.getCreatedAt()))

            .collect(Collectors.toList());

    }
 
 
   

    }
 
 

