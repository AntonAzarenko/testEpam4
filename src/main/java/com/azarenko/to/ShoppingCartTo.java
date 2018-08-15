package com.azarenko.to;


import com.azarenko.services.PeriodicalService;
import com.azarenko.services.SubscriptionTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartTo {
    @Autowired
    private SubscriptionTimeUtil timeUtil;

    @Autowired
    private PeriodicalService service;

    @Nullable
    private int periodicalId;

    @Nullable
    private String name;

    @Nullable
    private boolean firstHalfYear;

    @Nullable
    private boolean secondHalfYear;

    @Nullable
    private boolean year;

    public ShoppingCartTo create(int id) {
        name = service.get(id).getTitle();
        periodicalId = id;
        if (timeUtil.getHalfYear() == 0) {
            firstHalfYear = true;
            secondHalfYear = true;
            year = true;
        } else {
            firstHalfYear = false;
            secondHalfYear = true;
            year = false;
        }
        return this;
    }

    @Nullable
    public int getPeriodicalId() {
        return periodicalId;
    }

    public void setPeriodicalId(@Nullable int periodicalId) {
        this.periodicalId = periodicalId;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    @Nullable
    public boolean isFirstHalfYear() {
        return firstHalfYear;
    }

    public void setFirstHalfYear(@Nullable boolean firstHalfYear) {
        this.firstHalfYear = firstHalfYear;
    }

    @Nullable
    public boolean isSecondHalfYear() {
        return secondHalfYear;
    }

    public void setSecondHalfYear(@Nullable boolean secondHalfYear) {
        this.secondHalfYear = secondHalfYear;
    }

    @Nullable
    public boolean isYear() {
        return year;
    }

    public void setYear(@Nullable boolean year) {
        this.year = year;
    }
}
