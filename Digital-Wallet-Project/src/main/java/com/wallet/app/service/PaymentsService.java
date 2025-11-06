package com.wallet.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wallet.app.model.Payments;
import com.wallet.app.repository.PaymentsRepository;

@Service
public class PaymentsService  {

    private final PaymentsRepository paymentsRepository;

    public PaymentsService(PaymentsRepository paymentsRepository) {
        this.paymentsRepository = paymentsRepository;
    }


    public List<Payments> getPaymentsByUserId(Integer userId) {
        return paymentsRepository.findByUserUserId(userId);
    }
}