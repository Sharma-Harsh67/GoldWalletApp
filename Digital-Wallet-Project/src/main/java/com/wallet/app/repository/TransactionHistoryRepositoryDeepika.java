package com.wallet.transaction.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.wallet.transaction.entity.TransactionHistory;
import com.wallet.transaction.entity.Users;

import java.util.List;

public interface TransactionHistoryRepositoryDeepika extends JpaRepository<TransactionHistory, Integer> {

    List<TransactionHistory> findByUser(Users user);

}
 