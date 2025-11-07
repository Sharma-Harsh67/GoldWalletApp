package com.wallet.app.service;



import java.util.List;

import com.wallet.app.dto.TransactionHistoryDTO;

public interface TransactionHistoryService {

    List<TransactionHistoryDTO> getTransactionsByUserName(String name);

}
 