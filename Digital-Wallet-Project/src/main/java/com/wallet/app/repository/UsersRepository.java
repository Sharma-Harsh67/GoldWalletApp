package com.wallet.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wallet.app.model.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    // You can add custom queries if needed
	Users findByNameIgnoreCase(String name);
	List<Users> findByAddressId(Integer addressId);
}
