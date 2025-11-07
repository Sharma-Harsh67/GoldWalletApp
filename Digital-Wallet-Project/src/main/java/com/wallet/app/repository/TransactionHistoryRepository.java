package com.wallet.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.wallet.app.model.TransactionHistory;
import com.wallet.app.model.Users;

import java.util.List;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Integer> {

    List<TransactionHistory> findByUser(Users user);

}
 