package com.azarenko.model;

import java.math.BigDecimal;

/**
 * Класс хранит информацию о перодических изданиях
 */

public class Periodical extends AbstractBaseEntity {
    // заголовок
    private String title;

    //описание
    private String discription;

    //издатель
    private String publisher;

    // частота выхода в полугодие
    private int outputFrequency;

    // индекс издания
    private int index;

    // ограничение по возрасту
    private int ageLimit;

    //цена за экземпляр
    private BigDecimal price;

    public Periodical() {
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Periodical(Integer id, String title, String discription,
                      String publisher, int outputFrequency, int index, int ageLimit, BigDecimal price) {
        super(id);
        this.title = title;
        this.discription = discription;
        this.publisher = publisher;
        this.outputFrequency = outputFrequency;
        this.index = index;
        this.ageLimit = ageLimit;
        this.price = price;
    }

    //проверка на новый объект в базе
    public boolean isNew() {
        return id == null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public int getOutputFrequency() {
        return outputFrequency;
    }

    public void setOutputFrequency(int outputFrequency) {
        this.outputFrequency = outputFrequency;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
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
                ", discription='" + discription + '\'' +
                ", outputFrequency=" + outputFrequency +
                ", index=" + index +
                ", ageLimit=" + ageLimit +
                ", price=" + price +
                '}';
    }
}
