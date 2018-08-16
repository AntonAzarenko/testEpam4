package com.azarenko.repository;

import com.azarenko.model.Payment;

import java.util.List;

public interface PaymentRepository {

    void save(Payment payment);

    List<Payment> getAllByUserId(int userID);

    List<Payment> getAll();

    Payment getById(int paymetid);
}
