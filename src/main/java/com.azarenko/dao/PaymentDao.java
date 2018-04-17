package com.azarenko.dao;

import com.azarenko.model.AbstractBaseEntity;
import com.azarenko.model.Payment;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentDao extends DaoBase {

    Payment createPayment(int userId, BigDecimal price);

}
