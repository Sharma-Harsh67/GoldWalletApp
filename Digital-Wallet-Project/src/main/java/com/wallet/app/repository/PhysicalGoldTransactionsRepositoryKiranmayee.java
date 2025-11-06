package com.wallet.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wallet.app.model.PhysicalGoldTransactions;
import com.wallet.app.model.Users;

import java.util.List;
@Repository
public interface PhysicalGoldTransactionsRepositoryKiranmayee extends JpaRepository<PhysicalGoldTransactions, Integer> {
    List<PhysicalGoldTransactions> findByUser(Users user);
    List<PhysicalGoldTransactions> findByBranchBranchIdIn(List<Long> branchIds);
}