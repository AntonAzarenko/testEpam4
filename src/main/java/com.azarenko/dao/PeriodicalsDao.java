package com.azarenko.dao;


import com.azarenko.model.Periodicals;

import java.util.List;

public interface PeriodicalsDao {

    List<Periodicals> getCatalog();

    void add(Periodicals periodicals);

    void remove(int id);

    void update(Periodicals periodicals);

    Periodicals getPeriodicalById(int id);

}
