package com.azarenko.services.logic;

import com.azarenko.exceptions.ExceptionUtil;
import com.azarenko.exceptions.NotFoundException;
import com.azarenko.model.Periodical;
import com.azarenko.repository.PeriodicalReposiroty;


import com.azarenko.services.PeriodicalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class PeriodicalServiceImpl implements PeriodicalService {
    private static final Logger LOG = LoggerFactory.getLogger(PeriodicalServiceImpl.class);

    @Autowired
    PeriodicalReposiroty periodicalReposiroty;

    @Autowired
    private HistoryServiceImpl historyService;

    private BigDecimal checkToValidBD(String value) {
        BigDecimal price = null;
        try {
            price = BigDecimal.valueOf(Double.parseDouble(value));
        } catch (NumberFormatException e) {
            LOG.info("Number format exception PRICE");
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
    @Cacheable("periodicals")
    public List<Periodical> getAll() {
        return periodicalReposiroty.getAll();
    }

    @Override
    @Transactional
    @CacheEvict(value = "periodicals", allEntries = true)
    public Periodical save(Periodical periodical) {
        Objects.requireNonNull(periodical);
        if (chekIs(periodical)) {
            throw new NotFoundException("Add status is error - This periodical already exist");
            /*return null;*/
        }
        if (periodicalReposiroty.save(periodical) == null) {
            LOG.info("Add status is error - This periodical already exist");
            return null;
        }
        historyService.save(periodical);

        return periodical;
    }

    @Override
    public boolean remove(int id) {
        return ExceptionUtil.check(periodicalReposiroty.remove(id), id);
    }

    @Override
    public Periodical get(int id) {
        return periodicalReposiroty.get(id);
    }

    @Override
    @Transactional
    public Periodical search(String param, String value) {
        try {
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
        } catch (Exception e) {
            LOG.error("e");
        }
        return null;
    }

    @Override
    @Transactional
    public Periodical search(String value){
        List<Periodical> periodicals = getAll();
        for (Periodical per : periodicals){
            if(per.getTitle().equalsIgnoreCase(value)){
                return per;
            }
        }
        for (Periodical per : periodicals){
            if(String.valueOf(per.getIndex()).equalsIgnoreCase(value)){
                return per;
            }
        }
        for (Periodical per : periodicals){
            if(String.valueOf(per.getPrice()).equals(value)){
                return per;
            }
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

    @Override
    public boolean isArchive(Periodical periodical) {
        return periodical.isArchive();
    }

    @Override
    @CacheEvict(value = "periodicals", allEntries = true)
    public boolean setArchive(int id) {
        Periodical periodical = get(id);
        if (periodical.isArchive()) return false;
        periodical.setArchive(true);
        return save(periodical) != null;
    }

    @Transactional
    public boolean chekIs(Periodical periodical) {
        String title = periodical.getTitle();
        Periodical periodical1 = search("title", title);
        if (periodical1 == null) {
            return false;
        } else {
            if (!(periodical.equals(periodical1))) {
                periodical.setId(periodical1.getId());
                return false;
            }
        }
        periodical.setId(periodical1.getId());
        return true;
    }

}



