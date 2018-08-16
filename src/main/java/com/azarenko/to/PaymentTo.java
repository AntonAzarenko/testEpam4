package com.azarenko.to;

import com.azarenko.model.Payment;
import com.azarenko.services.PaymentService;
import com.azarenko.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class PaymentTo {

    @Autowired
    private PaymentService service;

    /*private LocalDateTime date;*/

    private String date;

    private int userID;

    private int count;

    private BigDecimal cost;

    public PaymentTo asPaymentTo(Payment payment) {
        this.date = TimeUtil.toString(payment.getDate());
        this.cost = payment.getPrice();
        this.count = service.getInPayDay(payment.getId()).size();
        this.userID = payment.getUserId();
        return new PaymentTo(this.date, this.userID, this.count, this.cost);
    }

    public PaymentTo(String date, int userID, int count, BigDecimal cost) {
        this.date = date;
        this.userID = userID;
        this.count = count;
        this.cost = cost;
    }

    public PaymentTo() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}


