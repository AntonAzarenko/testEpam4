package com.azarenko.services;

import com.azarenko.model.Periodicals;

import java.util.List;

public interface PeriodicalService {
    List<Periodicals> getCatalog();

    void add(Periodicals periodicals);

    void remove(int id);

    void update(Periodicals periodicals);

    Periodicals getPeriodical(int id);

}
