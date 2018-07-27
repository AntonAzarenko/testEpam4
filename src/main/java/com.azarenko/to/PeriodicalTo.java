package com.azarenko.to;

import com.azarenko.model.Periodical;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigDecimal;

public class PeriodicalTo {

    @NotEmpty
    private String title;

    @NotEmpty
    private String discription;

    @NotEmpty
    private String publisher;

    @NotEmpty
    private int outputFrequency;

    @NotEmpty
    private int index;

    @NotEmpty
    private int ageLimit;

    @NotEmpty
    private BigDecimal price;

    @NotEmpty
    private boolean archive;

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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
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

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }
    public Periodical asPeriodical(){
        return new Periodical(null,title,discription,publisher,outputFrequency,index,ageLimit,price);
    }
}
