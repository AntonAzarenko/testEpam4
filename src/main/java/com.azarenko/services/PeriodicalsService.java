package com.azarenko.services;


import com.azarenko.model.Periodicals;

import java.util.List;

public interface PeriodicalsService {

    List<Periodicals> getCatalog();

    void add(Periodicals periodicals);

    void remove(int id);

    void update(Periodicals periodicals);

    Periodicals getPublication(int id);

}
