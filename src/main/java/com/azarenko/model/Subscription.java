package com.azarenko.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "subscriptions")
public final class Subscription extends AbstractBaseEntity {
    @Column(name = "id_periodicals")
    private int periodicalId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "date_start")
    private LocalDateTime dateStart;

    @Column(name = "date_end")
    private LocalDateTime dateEnd;

    @Column(name = "payment_id")
    private int paymentId;

    public Subscription(int periodicalId, int userId, LocalDateTime dateStartSubcription, LocalDateTime dateEndSubscription, int paymentId) {
        this.periodicalId = periodicalId;
        this.userId = userId;
        this.dateStart = dateStartSubcription;
        this.dateEnd = dateEndSubscription;
        this.paymentId = paymentId;

    }

    public Subscription() {
    }

    public int getPeriodicalId() {
        return periodicalId;
    }

    public void setPeriodicalId(int periodicalId) {
        this.periodicalId = periodicalId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
}
