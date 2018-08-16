package com.azarenko.services;

import com.azarenko.model.Payment;
import com.azarenko.model.Subscription;
import com.azarenko.to.PaymentTo;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentService {
    void add(Payment payment) ;

    List<Payment> getPaymentList() ;

    List<Payment> getPaymentByUserId(int id);

    Payment create(int id);

    List<PaymentTo> getPayTo(List<Payment> list);

    List<Subscription> getInPayDay(int paymentId);




}
