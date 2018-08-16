package com.azarenko.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="payment")
public  class Payment extends AbstractBaseEntity {

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name="user_id")
    private  int userId;

    @Column(name="price")
    private  BigDecimal price;

    public Payment(LocalDateTime date, int userId, BigDecimal price) {
        this.date = date;
        this.userId = userId;
        this.price = price;
    }

    public Payment() {

    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
