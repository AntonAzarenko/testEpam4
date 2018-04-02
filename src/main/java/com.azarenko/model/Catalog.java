package com.azarenko.model;

import java.math.BigDecimal;

public class Catalog extends AbstractBaseEntity {

    private String title;

    private String description;

    private BigDecimal price;

    public Catalog(int id, String title, String description, int price) {
        super(id);
        this.title = title;
        this.description = description;
        this.price = BigDecimal.valueOf(price/100);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", id=" + id +
                '}';
    }
}
