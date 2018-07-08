package com.azarenko.services.logic;

import com.azarenko.exceptions.ExceptionUtil;
import com.azarenko.model.HistoryPrice;
import com.azarenko.model.Periodical;
import com.azarenko.repository.HistoryRepository;
import com.azarenko.services.HistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class HistoryServiceImpl implements HistoryService {

    private static final Logger LOG = LoggerFactory.getLogger(HistoryServiceImpl.class);
    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public List<HistoryPrice> getAllByPeriodicalId(int id) {
        return historyRepository.getAllByPeriodicalId(id);
    }

    @Override
    public List<HistoryPrice> searchAllByName(String name) {
        return historyRepository.searchAllByName(name);
    }

    @Override
    public void save(Periodical periodical) {
        Objects.requireNonNull(periodical);
        HistoryPrice historyPrice = create(periodical);

        if (!ExceptionUtil.check(historyPrice.getId())) {
            historyRepository.save(historyPrice);
            return;
        }
        if (round(periodical.getPrice()).compareTo(historyPrice.getPrice()) == 0) {
            return;
        }
        historyPrice.setDateEnd(new Date());
        historyRepository.save(historyPrice);
        historyRepository.save(create(periodical));
    }

    @Override
    public List<HistoryPrice> getAll() {
        return historyRepository.getAll();
    }

    @Override
    public List<HistoryPrice> searchBetween(Date start, Date end) {
        List<HistoryPrice> list = sort(start, end, getAll());
        return list;
    }


    private HistoryPrice create(Periodical periodical) {
        List<HistoryPrice> list = new ArrayList<>();
        // list = getAllByPeriodicalId(periodical.getId());
        HistoryPrice price = null;
        if ((list = getAllByPeriodicalId(periodical.getId())) != null) {
            for (HistoryPrice hp : list) {
                if (ExceptionUtil.check(hp.getIdPeriodical()) && hp.getDateEnd() == null) {
                    price = hp;
                    break;
                }
            }
        }
        if (price == null) {
            price = new HistoryPrice(periodical.getPrice(), periodical.getTitle(), periodical.getId(), new Date(), null);
        }
        return price;
    }

    private List<HistoryPrice> sort(Date start, Date end, List<HistoryPrice> list) {
        System.out.println(start.before(list.get(0).getDateStart()));
        return list;//todo
    }

    private BigDecimal round(BigDecimal price) {
        return price.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
