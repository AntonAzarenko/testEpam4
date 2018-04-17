package com.azarenko.services;

import com.azarenko.model.Payment;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentService {
    void add(Payment payment);
    List<Payment> getPaymentList();
    Payment getPaymentByUserId(int id);
    Payment createPayment(int userId, BigDecimal price);
}
