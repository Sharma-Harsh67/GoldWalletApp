package com.wallet.app.service;



import java.util.List;

import com.wallet.app.dto.TransactionHistoryDTODeepika;

public interface TransactionHistoryServiceDeepika {

    List<TransactionHistoryDTODeepika> getTransactionsByUserName(String name);

}
 