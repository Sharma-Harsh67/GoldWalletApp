package com.wallet.transaction.service;



import java.util.List;

import com.wallet.transaction.dto.TransactionHistoryDTODeepika;

public interface TransactionHistoryServiceDeepika {

    List<TransactionHistoryDTODeepika> getTransactionsByUserName(String name);

}
 