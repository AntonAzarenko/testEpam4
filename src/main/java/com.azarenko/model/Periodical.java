package com.azarenko.model;

import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Comparator;

/**
 * Класс хранит информацию о перодических изданиях
 */
@Entity
@Table(name = "catalog_periodicals")
public class Periodical extends AbstractBaseEntity implements Comparator<Periodical> {
    // заголовок
    @Column(name="title", nullable = false)
    @Nullable()
    private String title;

    //описание
    @Column(name = "discription")
    private String discription;

    //издатель
    @Column(name = "publisher")
    private String publisher;

    // частота выхода в полугодие
    @Column(name="output_frequncy")
    private int outputFrequency;

    // индекс издания
    @Column(name = "index")
    private int index;

    // ограничение по возрасту
    @Column(name = "age_limit")
    private int ageLimit;

    //цена за экземпляр
    @Column(name = "price")
    private BigDecimal price;

    public Periodical() {
    }

    public String getPublisher() {
        return publisher;
    }

    public Periodical(String title, String discription, String publisher, int outputFrequency, int index, int ageLimit, BigDecimal price) {
        this.title = title;
        this.discription = discription;
        this.publisher = publisher;
        this.outputFrequency = outputFrequency;
        this.index = index;
        this.ageLimit = ageLimit;
        this.price = price;
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


    @Override
    public  int compare(Periodical o1, Periodical o2) {
        return o1.title.compareTo(o2.title);
    }


}
