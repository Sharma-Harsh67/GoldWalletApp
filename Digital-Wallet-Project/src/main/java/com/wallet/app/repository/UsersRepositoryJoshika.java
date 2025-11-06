package com.wallet.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.wallet.app.model.Users;



@Repository

public interface UsersRepositoryJoshika extends JpaRepository<Users, Integer> {

   Users findByNameIgnoreCase(String name);

}
