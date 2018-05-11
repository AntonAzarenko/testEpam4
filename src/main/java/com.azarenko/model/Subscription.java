package com.azarenko.model;

import java.math.BigDecimal;
import java.util.Date;

public final class Subscription extends AbstractBaseEntity {

    private final String title;

    private final int periodicalId;

    private final int userId;

    private final String namePeriodical;

    private final Date dateStartSubcription;

    private final Date dateEndSubscription;

    private final BigDecimal price;

    private Subscription(SubscriptionBuild subscriptionBuild) {
        this.title = subscriptionBuild.title;
        this.periodicalId = subscriptionBuild.idPeriodical;
        this.namePeriodical = subscriptionBuild.namePeriodical;
        this.dateStartSubcription = subscriptionBuild.dateStartSubcription;
        this.dateEndSubscription = subscriptionBuild.dateEndSubscription;
        this.price = subscriptionBuild.price;
        this.userId = subscriptionBuild.userId;
    }

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

    public static class SubscriptionBuild {
        private int id;

        private String title;

        private int userId;

        private int idPeriodical;
        private String namePeriodical;

        private Date dateStartSubcription;

        private Date dateEndSubscription;

        private BigDecimal price;

        public SubscriptionBuild() {
        }

        public SubscriptionBuild id(int id) {
            this.id = id;
            return this;
        }

        public SubscriptionBuild title(String title) {
            this.title = title;
            return this;
        }

        public SubscriptionBuild userId(int userId) {
            this.userId = userId;
            return this;
        }

        public SubscriptionBuild namePeriodical(String name) {
            this.namePeriodical = name;
            return this;
        }

        public SubscriptionBuild idPeriodical(int id) {
            this.idPeriodical = id;
            return this;
        }

        public SubscriptionBuild dateStartSubcription(Date date) {
            this.dateStartSubcription = date;
            return this;
        }

        public SubscriptionBuild dateEndSubscription(Date date) {
            this.dateEndSubscription = date;
            return this;
        }

        public SubscriptionBuild price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Subscription build() {
            return new Subscription(this);
        }
    }
}
