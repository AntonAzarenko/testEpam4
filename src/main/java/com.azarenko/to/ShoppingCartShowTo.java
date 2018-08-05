package com.azarenko.to;


import com.azarenko.model.Periodical;
import com.azarenko.model.ShoppingCart;
import com.azarenko.services.PeriodicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class ShoppingCartShowTo {
    @Autowired
    private PeriodicalService service;
    @Nullable
    private int index;
    @Nullable
    private String title;
    @Nullable
    private String start;

    @Nullable
    public String getStart() {
        return start;
    }

    public void setStart(@Nullable String start) {
        this.start = start;
    }

    @Nullable
    public String getEnd() {
        return end;
    }

    public void setEnd(@Nullable String end) {
        this.end = end;
    }

    @Nullable
    private String end;
    @Nullable
    private int countPer;
    @Nullable
    private BigDecimal fullPrice;




    @Nullable
    public int getIndex() {
        return index;
    }

    public void setIndex(@Nullable int index) {
        this.index = index;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nullable String title) {
        this.title = title;
    }





    @Nullable
    public int getCountPer() {
        return countPer;
    }

    public void setCountPer(@Nullable int countPer) {
        this.countPer = countPer;
    }

    @Nullable
    public BigDecimal getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(@Nullable BigDecimal fullPrice) {
        this.fullPrice = fullPrice;
    }

    @Override
    public String toString() {
        return "ShoppingCartShowTo{" +
                "service=" + service +
                ", index=" + index +
                ", name='" + title + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", countPer=" + countPer +
                ", fullPrice=" + fullPrice +
                '}';
    }
}
