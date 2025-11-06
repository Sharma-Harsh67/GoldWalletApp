package com.wallet.transaction.controller;

import org.springframework.web.bind.annotation.*;

import com.wallet.transaction.dto.TransactionHistoryDTODeepika;
import com.wallet.transaction.service.TransactionHistoryServiceDeepika;

import java.util.List;

@RestController

@RequestMapping("/api/transactions")

public class TransactionHistoryControllerDeepika {
	

    private TransactionHistoryServiceDeepika txService;
    

    public TransactionHistoryControllerDeepika(TransactionHistoryServiceDeepika txService) {
		super();
		this.txService = txService;
	}


	@GetMapping("/by-name")

    public List<TransactionHistoryDTODeepika> getTransactionsByUserName(@RequestParam String name) {

        return txService.getTransactionsByUserName(name);

    }

}
 