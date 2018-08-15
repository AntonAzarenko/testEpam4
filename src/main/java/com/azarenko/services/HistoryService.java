package com.azarenko.services;

import com.azarenko.model.HistoryPrice;
import com.azarenko.model.Periodical;

import java.util.Date;
import java.util.List;

public interface HistoryService {

    List<HistoryPrice> getAllByPeriodicalId(int id);

    List<HistoryPrice> searchAllByName(String name);

    void save(Periodical periodical);

    List<HistoryPrice> getAll();

    List<HistoryPrice> searchBetween(Date start, Date end);





}
