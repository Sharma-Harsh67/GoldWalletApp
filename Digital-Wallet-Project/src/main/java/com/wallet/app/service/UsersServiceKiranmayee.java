package com.digitalgoldwallet.service;

import com.digitalgoldwallet.dto.TransactionQuantityDTOKiranmayee;
import com.digitalgoldwallet.dto.UsersDTOKiranmayee;
import com.digitalgoldwallet.model.PhysicalGoldTransactions;
import com.digitalgoldwallet.model.Users;
import com.digitalgoldwallet.repository.UsersRepositoryKiranmayee;
import com.digitalgoldwallet.repository.PhysicalGoldTransactionsRepositoryKiranmayee;
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
            .map(user -> new UsersDTOKiranmayee(user.getName(), user.getEmail()))
            .collect(Collectors.toList());
    }


   
    }

