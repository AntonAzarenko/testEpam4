package com.azarenko.services;

import com.azarenko.dao.DaoException;
import com.azarenko.model.Payment;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentService {
    void add(Payment payment) throws DaoException;
    List<Payment> getPaymentList() throws DaoException;
    Payment getPaymentByUserId(int id);
    Payment createPayment(int userId, BigDecimal price);
}
