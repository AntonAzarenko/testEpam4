package com.azarenko.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="shopping_cart")
@NamedQueries({
        @NamedQuery(name=ShoppingCart.DELETE,query = "DELETE FROM ShoppingCart sh")
})
public class ShoppingCart extends AbstractBaseEntity {

    public static final String DELETE = "ShoppingCart.delete";

    @Column(name="userId")
    private  int userID;

    @Column(name="id_periodicals")
    private int periodicalId;

    @Column(name = "date_Start")
    private Date start;

    @Column(name="Date_end")
    private Date end;

    @Column(name="price")
    private BigDecimal price;

    @Column(name= "countPer")
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


    public ShoppingCart(int userID, int periodicalId, Date start, Date end, BigDecimal price, int countPer) {
        this.userID = userID;
        this.periodicalId = periodicalId;
        this.start = start;
        this.end = end;
        this.price = price;
        this.countPer = countPer;
    }

    public ShoppingCart(Integer id, int userID, int periodicalId, Date start, Date end, BigDecimal price, int countPer) {
        super(id);
        this.userID = userID;
        this.periodicalId = periodicalId;
        this.start = start;
        this.end = end;
        this.price = price;
        this.countPer = countPer;
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
