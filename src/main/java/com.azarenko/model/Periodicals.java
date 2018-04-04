package com.azarenko.model;

import java.math.BigDecimal;

/**
 * Класс хранит информацию о перодических изданиях
 */
public class Periodicals extends AbstractBaseEntity {

    private String title;

    private String description;

    private  BigDecimal price;

    public Periodicals(int id, String title, String description, BigDecimal price) {
        super(id);
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public Periodicals() {
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
        return "Periodicals{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", id=" + id +
                '}';
    }

    /**
     *
     * @param price
     * @return
     */
    private BigDecimal getBigDecimal(int price){
        double temp = price;
        temp = temp/100;
        BigDecimal bigDecimal = new BigDecimal(temp);

        return bigDecimal;
    }




}
