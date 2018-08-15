package com.azarenko.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
@Entity
@Table(name = "subscriptions")
public final class Subscription extends AbstractBaseEntity {
    @Column(name = "id_periodicals")
    public int periodicalId;

    @Column(name = "user_id")
    public int userId;

    @Column(name = "date_start")
    public LocalDateTime dateStart;

    @Column(name = "date_end")
    public LocalDateTime dateEnd;

    public Subscription(int periodicalId, int userId, LocalDateTime dateStartSubcription, LocalDateTime dateEndSubscription) {
        this.periodicalId = periodicalId;
        this.userId = userId;
        this.dateStart = dateStartSubcription;
        this.dateEnd = dateEndSubscription;

    }

    public Subscription() {
    }

    public int getUserId() {
        return userId;
    }


    public int getPeriodicalId() {
        return periodicalId;
    }

    public LocalDateTime getDateStartSubcription() {
        return dateStart;
    }

    public LocalDateTime getDateEndSubscription() {
        return dateEnd;
    }

    public void setPeriodicalId(int periodicalId) {
        this.periodicalId = periodicalId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDateStartSubcription(LocalDateTime dateStartSubcription) {
        this.dateStart = dateStartSubcription;
    }

    public void setDateEndSubscription(LocalDateTime dateEndSubscription) {
        this.dateEnd = dateEndSubscription;
    }


}
