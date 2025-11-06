package com.wallet.transaction.serviceimpl;

import org.springframework.stereotype.Service;

import com.wallet.transaction.dto.TransactionHistoryDTODeepika;
import com.wallet.transaction.entity.Users;
import com.wallet.transaction.repository.TransactionHistoryRepositoryDeepika;
import com.wallet.transaction.repository.UsersRepositoryDeepika;
import com.wallet.transaction.service.TransactionHistoryServiceDeepika;

import java.util.List;

import java.util.stream.Collectors;

@Service

public class TransactionHistoryServiceImplDeepika implements TransactionHistoryServiceDeepika {
    private UsersRepositoryDeepika usersRepository;
    private TransactionHistoryRepositoryDeepika txRepo;
    
    

    public TransactionHistoryServiceImplDeepika(UsersRepositoryDeepika usersRepository, TransactionHistoryRepositoryDeepika txRepo) {
		super();
		this.usersRepository = usersRepository;
		this.txRepo = txRepo;
	}



	@Override
    public List<TransactionHistoryDTODeepika> getTransactionsByUserName(String name) {

        Users user = usersRepository.findAll().stream()

                .filter(u -> u.getName().equalsIgnoreCase(name.trim()))

                .findFirst()

                .orElseThrow(() -> new RuntimeException("User not found: " + name));

        return txRepo.findByUser(user).stream()

                .map(tx -> new TransactionHistoryDTODeepika(

                        tx.getTransactionId(),

                        tx.getQuantity(),

                        tx.getBranchId(),

                        user.getName()

                ))

                .collect(Collectors.toList());

    }

}
 