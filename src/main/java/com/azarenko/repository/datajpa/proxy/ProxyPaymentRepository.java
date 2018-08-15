package com.azarenko.repository.datajpa.proxy;

import com.azarenko.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProxyPaymentRepository extends JpaRepository<Payment, Integer> {
    List<Payment> findAllById(Integer userID);
}
