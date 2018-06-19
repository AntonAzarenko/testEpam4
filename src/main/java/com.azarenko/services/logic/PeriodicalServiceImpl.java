package com.azarenko.services.logic;

import com.azarenko.exceptions.ExceptionUtil;
import com.azarenko.model.Periodical;
import com.azarenko.repository.PeriodicalReposiroty;


import com.azarenko.services.PeriodicalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class PeriodicalServiceImpl implements PeriodicalService {
    private static final Logger LOG = LoggerFactory.getLogger(PeriodicalServiceImpl.class);

    @Autowired
    PeriodicalReposiroty periodicalReposiroty;

    private BigDecimal checkToValidBD(String value) {
        BigDecimal price = null;
        try {
            price = BigDecimal.valueOf(Double.parseDouble(value));
        } catch (NumberFormatException e) {
            LOG.info("NumberFormatException PRICE");
        }
        return price;
    }

    private int checkToValidInt(String value) {
        int id = 0;
        try {
            id = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            LOG.error("Number format exception");
        }
        return id;
    }

    @Override
    public List<Periodical> getAll() {
        return periodicalReposiroty.getAll();
    }

    @Override
    public Periodical save(Periodical periodical) {
        Objects.requireNonNull(periodical);

        return periodicalReposiroty.save(periodical);
    }

    @Override
    public boolean remove(int id) {
        return ExceptionUtil.check(periodicalReposiroty.remove(id), id);
    }

    @Override
    public Periodical get(int id) {
        return ExceptionUtil.check(periodicalReposiroty.get(id), id);
    }

    @Override
    public Periodical search(String param, String value) {
        switch (param) {
            case "title":
                return periodicalReposiroty.search(value);

            case "index":
                int index = checkToValidInt(value);
                return periodicalReposiroty.search(index);

            case "price":
                BigDecimal price = checkToValidBD(value);
                return periodicalReposiroty.search(price);
        }
        return null;
    }

    @Override
    public List<Periodical> sortByName(List<Periodical> list) {
        Comparator<Periodical> comparator = (o1, o2) -> new Periodical().compare(o1, o2);
        Collections.sort(list, comparator);
        return list;
    }

    @Override
    public List<Periodical> sortByIndex(List<Periodical> list) {
        Comparator<Periodical> perIndex = Comparator.comparing(Periodical::getIndex);
        Collections.sort(list, perIndex);
        return list;
    }

}



