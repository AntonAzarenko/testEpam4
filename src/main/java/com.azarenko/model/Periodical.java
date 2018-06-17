package com.azarenko.model;

import java.math.BigDecimal;

/**
 * Класс хранит информацию о перодических изданиях
 */

public class Periodical extends AbstractBaseEntity {

    private String title;
    private int outputFrequency;

    public int getOutputFrequency() {
        return outputFrequency;
    }

    public void setOutputFrequency(int outputFrequency) {
        this.outputFrequency = outputFrequency;
    }

    private String description;

    private  BigDecimal price;

    private boolean New;

    public boolean isNew() {
        if(id==null)
        return true;
        else return false;
    }

    public void setNew(boolean aNew) {
        New = aNew;
    }

    public Periodical(Integer id, String title, String description, int outputFrequency, BigDecimal price) {
        super(id);
        this.title = title;
        this.outputFrequency = outputFrequency;
        this.description = description;
        this.price = price;
    }

    public Periodical() {
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
        return "Periodical{" +
                "title='" + title + '\'' +
                ", outputFrequency=" + outputFrequency +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", id=" + id +
                '}';
    }
}
