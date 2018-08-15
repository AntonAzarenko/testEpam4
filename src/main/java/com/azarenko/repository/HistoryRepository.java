package com.azarenko.repository;

import com.azarenko.model.HistoryPrice;

import java.util.Date;
import java.util.List;

public interface HistoryRepository {

    List<HistoryPrice> getAllByPeriodicalId(int id);

    List<HistoryPrice> searchAllByName(String name);

    void save(HistoryPrice historyPrice);

    List<HistoryPrice> getAll();

}
