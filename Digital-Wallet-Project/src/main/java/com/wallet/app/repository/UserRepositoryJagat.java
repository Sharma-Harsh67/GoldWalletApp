package com.wallet.app.repository;

import com.wallet.app.model.UserJagat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryJagat extends JpaRepository<UserJagat, Integer> {}
