package com.wallet.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wallet.app.model.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    // You can add custom queries if needed
}
