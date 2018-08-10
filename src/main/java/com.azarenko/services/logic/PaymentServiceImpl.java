package com.azarenko.services.logic;

import com.azarenko.model.Payment;
import com.azarenko.repository.PaymentRepository;
import com.azarenko.services.PaymentService;
import com.azarenko.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private ShoppingCartService cartService;

    @Autowired
    PaymentRepository repository;

    @Override
    public void add(Payment payment) {
        repository.save(payment);
    }

    @Override
    public List<Payment> getPaymentList() {
        return null;
    }

    @Override
    public Payment getPaymentByUserId(int id) {
        return null;
    }

    @Override
    public Payment create(int id) {
        return new Payment(LocalDateTime.now(),id, cartService.getFullPriceForPayment(id));
    }


}
