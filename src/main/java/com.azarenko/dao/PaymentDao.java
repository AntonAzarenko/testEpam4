package com.azarenko.dao;

import com.azarenko.model.Payment;

import java.math.BigDecimal;

public interface PaymentDao extends BaseDao<Payment> {
    Payment createPayment(int userId, BigDecimal price);

}
