package com.azarenko.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Access(AccessType.FIELD)
@Table(name = "shopping_cart")
@NamedQueries({
        @NamedQuery(name = ShoppingCart.DELETE, query = "DELETE FROM ShoppingCart sh where sh.userID=:id"),
        @NamedQuery(name = ShoppingCart.ALL_SORTED, query = "SELECT sc FROM ShoppingCart sc WHERE sc.userID =:id")
})
public class ShoppingCart extends AbstractBaseEntity {

    public static final String DELETE = "ShoppingCart.delete";
    public static final String ALL_SORTED = "ShoppingCart.allSorted";

    @Column(name = "userId")
    private int userID;

    @Column(name = "id_periodicals")
    private int periodicalId;

    @Column(name = "date_Start")
    private Date start;

    @Column(name = "Date_end")
    private Date end;

    @Column(name = "price")
    private BigDecimal price;

    /**
     * this variable has about subscription time.
     * @ 0 equals first half year, @ 1 equals second half year, @ 2 equals year
     */
    @Column(name = "time")
    private int time;

    @Column(name = "countPer")
    private int countPer;

    public ShoppingCart(int userID, int periodicalId, Date start, Date end, BigDecimal price, int countPer, int time) {
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

    public ShoppingCart() {
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "userID=" + userID +
                ", periodicalId=" + periodicalId +
                ", start=" + start +
                ", end=" + end +
                ", price=" + price +
                ", time=" + time +
                ", countPer=" + countPer +
                '}';
    }
}
