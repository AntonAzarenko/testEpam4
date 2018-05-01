package com.azarenko.model;

import java.math.BigDecimal;
import java.util.Date;

public class ShoppingCart extends AbstractBaseEntity {
    private  int userID;

    private int periodicalId;

    private Date start;

    private Date end;

    private BigDecimal price;
    private int countPer;

    public int getCountPer() {
        return countPer;
    }

    public void setCountPer(int countPer) {
        this.countPer = countPer;
    }

    public BigDecimal getPrice() {
        return price;
    }



    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPeriodicalId() {
        return periodicalId;
    }

    public ShoppingCart() {
    }

    public void setPeriodicalId(int periodicalId) {
        this.periodicalId = periodicalId;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public ShoppingCart(int id, int userID, int countPer, int periodicalId, Date start, Date end, BigDecimal price) {

        super(id);
        this.countPer = countPer;
        this.userID = userID;
        this.periodicalId = periodicalId;
        this.start = start;
        this.end = end;
        this.price = price;
    }
}
