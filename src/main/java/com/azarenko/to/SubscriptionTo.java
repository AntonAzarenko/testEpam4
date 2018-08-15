package com.azarenko.to;

import com.azarenko.model.Periodical;
import com.azarenko.model.Subscription;
import com.azarenko.services.PeriodicalService;
import com.azarenko.services.SubscriptionTimeUtil;
import com.azarenko.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionTo {

    @Autowired
    private PeriodicalService service;

    @Autowired
    SubscriptionTimeUtil subscriptionTimeUtil;

    private String title;

    private String start;

    private String end;

    private int countPer;

    private  int countInterToEnd;

    public SubscriptionTo() {
    }

    public SubscriptionTo asSubscription(Subscription subscription){
        Periodical periodical = service.get(subscription.getPeriodicalId());
        this.title = periodical.getTitle();
        this.start = TimeUtil.toString(subscription.getDateStartSubcription());
        this.end = TimeUtil.toString(subscription.getDateEndSubscription());
        this.countPer = periodical.getOutputFrequency();
        this.countInterToEnd =  subscriptionTimeUtil.getNumberOfExist(periodical);
        return new SubscriptionTo(this.title,  this.start, this.end, this.countPer, this.countInterToEnd);
    }

    public SubscriptionTo(String title, String start, String end, int countPer, int countInterToEnd) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.countPer = countPer;
        this.countInterToEnd = countInterToEnd;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getCountPer() {
        return countPer;
    }

    public void setCountPer(int countPer) {
        this.countPer = countPer;
    }

    public int getCountInterToEnd() {
        return countInterToEnd;
    }

    public void setCountInterToEnd(int countInterToEnd) {
        this.countInterToEnd = countInterToEnd;
    }
}
