package com.azarenko.model;



import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.Date;

@Entity
@Table(name = "history_price")
@NamedQueries({
        @NamedQuery(name = HistoryPrice.AlL_SORTED, query = "SELECT hp from HistoryPrice hp"),
        @NamedQuery(name = HistoryPrice.NAME, query = "SELECT hp FROM HistoryPrice hp WHERE hp.namePeriodical=:namePeriodical"),
        @NamedQuery(name=HistoryPrice.PERIODICAL_ID, query = "SELECT hp FROM HistoryPrice hp WHERE hp.idPeriodical=:idPeriodical")
})
public class HistoryPrice extends AbstractBaseEntity implements Comparator<HistoryPrice> {

    public static final String NAME = "HistoryPrice.name";
    public static final String AlL_SORTED = "HistoryPrice.allSorted";
    public static final String PERIODICAL_ID = "HistoryPrice.periodicalId";

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "name_periodicals")
    private String namePeriodical;

    @Column(name = "id_periodicals")
    private Integer idPeriodical;

    @Column(name="date_start")
    private Date dateStart;

    @Column(name="date_end")
    private Date dateEnd;

    public HistoryPrice() {
    }

    public HistoryPrice(BigDecimal price, String namePeriodical, int idPeriodical, Date dateStart, Date dateEnd) {
        this.price = price;
        this.namePeriodical = namePeriodical;
        this.idPeriodical = idPeriodical;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public HistoryPrice(Integer id, BigDecimal price, String namePeriodical, int idPeriodical, Date dateStart, Date dateEnd) {
        super(id);
        this.price = price;
        this.namePeriodical = namePeriodical;
        this.idPeriodical = idPeriodical;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        this.price = new BigDecimal(df.format(Double.parseDouble(String.valueOf(price))));
    }

    public String getNamePeriodical() {
        return namePeriodical;
    }

    public void setNamePeriodical(String namePeriodical) {
        this.namePeriodical = namePeriodical;
    }

    public Integer getIdPeriodical() {
        return idPeriodical;
    }

    public void setIdPeriodical(Integer idPeriodical) {
        this.idPeriodical = idPeriodical;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }


    @Override
    public int compare(HistoryPrice o1, HistoryPrice o2) {
        return o1.dateStart.compareTo(o2.dateStart);
    }
}
