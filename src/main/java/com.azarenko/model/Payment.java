package com.azarenko.model;

import java.math.BigDecimal;
import java.util.Date;

public final class Payment extends AbstractBaseEntity {
    private final Date date;

    private final int userId;

    private final BigDecimal price;

    private Payment(PaymentBuilder paymentBuilder) {
        super(paymentBuilder.id);
        this.date = paymentBuilder.date;
        this.userId = paymentBuilder.userId;
        this.price = paymentBuilder.price;

    }

    public Date getDate() {
        return date;
    }

    public int getUserId() {
        return userId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public static class PaymentBuilder {
        private int id;

        private Date date;

        private int userId;

        private BigDecimal price;

        public PaymentBuilder() {
        }

        public PaymentBuilder id(int id) {
            this.id = id;
            return this;
        }

        public PaymentBuilder date(Date date) {
            this.date = date;
            return this;
        }

        public PaymentBuilder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public PaymentBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Payment build() {
            return new Payment(this);
        }
    }
}
