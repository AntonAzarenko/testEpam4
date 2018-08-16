package com.azarenko.services.logic;

import com.azarenko.model.Payment;
import com.azarenko.model.Subscription;
import com.azarenko.repository.PaymentRepository;
import com.azarenko.services.PaymentService;
import com.azarenko.services.ShoppingCartService;
import com.azarenko.services.SubscriptionService;
import com.azarenko.to.PaymentTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private ShoppingCartService cartService;

    @Autowired
    private SubscriptionService subscription;

    @Autowired
    PaymentRepository repository;

    @Autowired
    PaymentTo to;

    @Override
    public void add(Payment payment) {
        repository.save(payment);
    }

    @Override
    public List<Payment> getPaymentList() {
        return null;
    }

    @Override
    public List<Payment> getPaymentByUserId(int id) {
        return repository.getAllByUserId(id);
    }

    @Override
    public Payment create(int id) {
        return new Payment(LocalDateTime.now(),id, cartService.getFullPriceForPayment(id));
    }

    @Override
    public List<PaymentTo> getPayTo(List<Payment> list) {
        List<PaymentTo> payments = new ArrayList<>();
        for(Payment current : list){
            payments.add(to.asPaymentTo(current));
        }
        return payments;
    }

    @Override
    public List<Subscription> getInPayDay(int paymentId) {
        return subscription.getAllByPaymentId(paymentId);
    }


}
