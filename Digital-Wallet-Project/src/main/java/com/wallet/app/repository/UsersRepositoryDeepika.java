package com.wallet.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wallet.transaction.entity.Users;
@Repository
public interface UsersRepositoryDeepika extends JpaRepository<Users, Integer> {
   Users findByNameIgnoreCase(String name);
}