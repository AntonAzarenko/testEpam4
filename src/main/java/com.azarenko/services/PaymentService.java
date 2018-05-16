package com.azarenko.services;

import com.azarenko.dao.DaoException;
import com.azarenko.model.Payment;

import java.util.List;

public interface PaymentService {
    void add(Payment payment) throws DaoException, ServiceException;

    List<Payment> getPaymentList() throws DaoException, ServiceException;

    //TODO
    Payment getPaymentByUserId(int id) throws DaoException, ServiceException;


}
