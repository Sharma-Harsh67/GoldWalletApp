package com.wallet.app.service;

import com.wallet.app.dto.TransactionQuantityDTOKiranmayee;
import com.wallet.app.model.PhysicalGoldTransactions;
import com.wallet.app.model.Users;
import com.wallet.app.repository.UsersRepositoryKiranmayee;
import com.wallet.app.repository.PhysicalGoldTransactionsRepositoryKiranmayee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhysicalGoldTransactionsServiceKiranmayee {

 
    private UsersRepositoryKiranmayee usersRepository;

 
    private PhysicalGoldTransactionsRepositoryKiranmayee transactionsRepository;
    public PhysicalGoldTransactionsServiceKiranmayee(UsersRepositoryKiranmayee usersRepository,
            PhysicalGoldTransactionsRepositoryKiranmayee transactionsRepository) {
this.usersRepository = usersRepository;
this.transactionsRepository = transactionsRepository;
    }

    
    public List<TransactionQuantityDTOKiranmayee> getQuantitiesWithUserName(String name) {
        String trimmedName = name.trim();

        Users user = usersRepository.findAll().stream()
                .filter(u -> u.getName().equalsIgnoreCase(trimmedName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found with name: " + name));

        return transactionsRepository.findByUser(user).stream()
                .map(tx -> new TransactionQuantityDTOKiranmayee(user.getName(), tx.getQuantity()))
                .collect(Collectors.toList());
    }
}