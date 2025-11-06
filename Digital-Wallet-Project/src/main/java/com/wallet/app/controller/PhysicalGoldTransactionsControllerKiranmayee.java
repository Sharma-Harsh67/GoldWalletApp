package com.wallet.app.controller;

import com.wallet.app.dto.TransactionQuantityDTOKiranmayee;
import com.wallet.app.service.PhysicalGoldTransactionsServiceKiranmayee;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/physical-transactions")
public class PhysicalGoldTransactionsControllerKiranmayee {
	private PhysicalGoldTransactionsServiceKiranmayee transactionService;

	public PhysicalGoldTransactionsControllerKiranmayee(PhysicalGoldTransactionsServiceKiranmayee transactionService) {
		super();
		this.transactionService = transactionService;
	}

	@GetMapping("/quantities-by-name")
	public List<TransactionQuantityDTOKiranmayee> getQuantitiesWithUserName(@RequestParam String name) {
		return transactionService.getQuantitiesWithUserName(name);
	}
}