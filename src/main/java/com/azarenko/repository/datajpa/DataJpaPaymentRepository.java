package com.azarenko.repository.datajpa;

import com.azarenko.model.Payment;
import com.azarenko.repository.PaymentRepository;
import com.azarenko.repository.datajpa.proxy.ProxyPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaPaymentRepository implements PaymentRepository {

    @Autowired
    private ProxyPaymentRepository proxy;

    @Override
    public void save(Payment payment) {
        proxy.save(payment);
    }

    @Override
    public List<Payment> getAllByUserId(int userID) {
        return proxy.findAllById(userID);
    }

    @Override
    public List<Payment> getAll() {
        return proxy.findAll();
    }

    @Override
    public Payment getById(int paymentid) {
        return proxy.getById(paymentid);
    }
}
