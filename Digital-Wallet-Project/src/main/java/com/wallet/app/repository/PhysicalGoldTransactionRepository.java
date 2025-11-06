package com.wallet.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wallet.app.model.PhysicalGoldTransactions;
import com.wallet.app.model.Users;

import java.util.List;

@Repository
public interface PhysicalGoldTransactionRepository extends JpaRepository<PhysicalGoldTransactions, Long> {
    
    List<PhysicalGoldTransactions> findByUser(Users user);
    
    List<PhysicalGoldTransactions> findByBranchBranchIdIn(List<Long> branchIds);
    
    @Query("SELECT pgt FROM PhysicalGoldTransactions pgt JOIN pgt.user u WHERE u.name = :userName")
    List<PhysicalGoldTransactions> findByUserName(@Param("userName") String userName);
}