package com.wallet.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wallet.app.model.Payments;

public interface PaymentsRepository extends JpaRepository<Payments, Integer> {
    List<Payments> findByUserUserId(Integer userId);
}

