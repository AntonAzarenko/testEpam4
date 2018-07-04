package com.azarenko.services;

import com.azarenko.exceptions.NotFoundException;
import com.azarenko.model.Periodical;

import java.util.List;

public interface PeriodicalService {

    List<Periodical> getAll();

    Periodical save(Periodical periodical);

    boolean remove(int id) throws NotFoundException;

    Periodical get(int id) throws NotFoundException;

    Periodical search(String param, String value);

    List<Periodical> sortByName(List<Periodical> list);

    List<Periodical> sortByIndex(List<Periodical> list);

    boolean isArchive(Periodical periodical);

    boolean setArchive(int id);

}
