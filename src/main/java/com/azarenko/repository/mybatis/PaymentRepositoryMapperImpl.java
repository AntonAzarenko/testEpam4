package com.azarenko.repository.mybatis;

import com.azarenko.model.Payment;
import com.azarenko.repository.PaymentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentRepositoryMapperImpl implements PaymentRepository {
    @Override
    public void save(Payment payment) {

    }

    @Override
    public List<Payment> getAllByUserId(int userID) {
        return null;
    }

    @Override
    public List<Payment> getAll() {
        return null;
    }

    @Override
    public Payment getById(int paymetid) {
        return null;
    }
}
