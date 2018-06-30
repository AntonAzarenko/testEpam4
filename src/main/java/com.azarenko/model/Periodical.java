package com.azarenko.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Comparator;

/**
 * Класс хранит информацию о перодических изданиях
 */
@Entity
@Table(name = "catalog_periodicals")
@NamedQueries({
        @NamedQuery(name = Periodical.DELETE, query = "DELETE FROM Periodical p WHERE p.id=:id"),
        @NamedQuery(name = Periodical.TITLE, query = "SELECT p FROM Periodical p WHERE p.title=:title"),
        @NamedQuery(name = Periodical.All_SORTED, query = "SELECT p FROM Periodical p"),
        @NamedQuery(name = Periodical.INDEX, query = "SELECT p FROM Periodical p WHERE p.index=:index"),
        @NamedQuery(name = Periodical.PRICE, query = "select p from Periodical p WHERE p.price=:price")
})
public class Periodical extends AbstractBaseEntity implements Comparator<Periodical> {

    public static final String DELETE = "Periodical.delete";
    public static final String All_SORTED = "Periodical.allSorted";
    public static final String TITLE = "Periodical.title";
    public static final String PRICE = "Periodical.price";
    public static final String INDEX = "Periodical.index";

    // заголовок
    @Column(name = "title", nullable = false, unique = true)
    @Nullable()
    private String title;

    //описание
    @Column(name = "discription")
    private String discription;

    //издатель
    @Column(name = "publisher")
    private String publisher;

    // частота выхода в полугодие
    @Column(name = "output_frequency")
    private int outputFrequency;

    // индекс издания
    @Column(name = "per_index")
    private int index;

    // ограничение по возрасту
    @Column(name = "age_limit")
    private int ageLimit;

    //цена за экземпляр
    @Column(name = "price")
    private BigDecimal price;
    private boolean archive;

    public Periodical() {
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

    @Column(name = "archive")
    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
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
    public int compare(Periodical o1, Periodical o2) {
        return o1.title.compareTo(o2.title);
    }


}
