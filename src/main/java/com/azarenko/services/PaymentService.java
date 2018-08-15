package com.azarenko.services;

import com.azarenko.model.Payment;

import java.util.List;

public interface PaymentService {
    void add(Payment payment) ;

    List<Payment> getPaymentList() ;

    Payment getPaymentByUserId(int id);

    Payment create(int id);




}
