package com.azarenko.to;

import com.azarenko.model.Payment;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class PaymentTo {
    private LocalDateTime time;

    private int UserID;

    private BigDecimal price;

    public PaymentTo create(Payment payment){
        setPrice(payment.getPrice());
        setTime(payment.getDate());
        setUserID(payment.getUserId());
        return this;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public BigDecimal getPrice() {

        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
