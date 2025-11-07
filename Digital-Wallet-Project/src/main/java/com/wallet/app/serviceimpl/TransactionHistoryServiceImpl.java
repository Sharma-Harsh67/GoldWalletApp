package com.wallet.app.serviceimpl;

import org.springframework.stereotype.Service;

import com.wallet.app.dto.TransactionHistoryDTO;
import com.wallet.app.model.Users;
import com.wallet.app.repository.TransactionHistoryRepository;
import com.wallet.app.repository.UsersRepositoryDeepika;
import com.wallet.app.service.TransactionHistoryService;

import java.util.List;

import java.util.stream.Collectors;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {
	private UsersRepositoryDeepika usersRepository;
	private TransactionHistoryRepository txRepo;

	public TransactionHistoryServiceImpl(UsersRepositoryDeepika usersRepository,
			TransactionHistoryRepository txRepo) {
		super();
		this.usersRepository = usersRepository;
		this.txRepo = txRepo;
	}

	@Override
	public List<TransactionHistoryDTO> getTransactionsByUserName(String name) {

		Users user = usersRepository.findAll().stream()

				.filter(u -> u.getName().equalsIgnoreCase(name.trim()))

				.findFirst()

				.orElseThrow(() -> new RuntimeException("User not found: " + name));

		return txRepo.findByUser(user).stream()

				.map(tx -> new TransactionHistoryDTO(

						tx.getTransactionId(),

						tx.getQuantity(),

						tx.getBranchId(),

						user.getName(), tx.getAmount(),

tx.getTransactionType() != null ? tx.getTransactionType().toString() : null,
    tx.getTransactionStatus() != null ? tx.getTransactionStatus().toString() : null


		

				))

				.collect(Collectors.toList());

	}

}
