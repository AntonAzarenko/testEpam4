package com.azarenko.model;

import java.math.BigDecimal;
import java.util.Date;

public final class Subscription extends AbstractBaseEntity {

    public String title;

    public int periodicalId;

    public int userId;

    public String namePeriodical;

    public Date dateStartSubcription;

    public Date dateEndSubscription;

    public BigDecimal price;


    public int getUserId() {
        return userId;
    }

    public String getNamePeriodical() {
        return namePeriodical;
    }

    public String getTitle() {
        return title;
    }

    public int getPeriodicalId() {
        return periodicalId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Date getDateStartSubcription() {
        return dateStartSubcription;
    }

    public Date getDateEndSubscription() {
        return dateEndSubscription;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPeriodicalId(int periodicalId) {
        this.periodicalId = periodicalId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setNamePeriodical(String namePeriodical) {
        this.namePeriodical = namePeriodical;
    }

    public void setDateStartSubcription(Date dateStartSubcription) {
        this.dateStartSubcription = dateStartSubcription;
    }

    public void setDateEndSubscription(Date dateEndSubscription) {
        this.dateEndSubscription = dateEndSubscription;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
