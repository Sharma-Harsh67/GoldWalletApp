package com.digitalgoldwallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitalgoldwallet.model.PhysicalGoldTransactions;
import com.digitalgoldwallet.model.Users;

import java.util.List;
@Repository
public interface PhysicalGoldTransactionsRepositoryKiranmayee extends JpaRepository<PhysicalGoldTransactions, Integer> {
    List<PhysicalGoldTransactions> findByUser(Users user);
}