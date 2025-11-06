package com.digitalgoldwallet.controller;

import com.digitalgoldwallet.dto.TransactionQuantityDTOKiranmayee;
import com.digitalgoldwallet.service.PhysicalGoldTransactionsServiceKiranmayee;
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