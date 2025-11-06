package com.wallet.app.serviceimpl;

import org.springframework.stereotype.Service;

import com.wallet.app.dto.TransactionHistoryDTODeepika;
import com.wallet.app.model.Users;
import com.wallet.app.repository.TransactionHistoryRepositoryDeepika;
import com.wallet.app.repository.UsersRepositoryDeepika;
import com.wallet.app.service.TransactionHistoryServiceDeepika;

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
 