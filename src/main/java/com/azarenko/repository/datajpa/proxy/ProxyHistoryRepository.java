package com.azarenko.repository.datajpa.proxy;

import com.azarenko.model.HistoryPrice;
import com.azarenko.repository.HistoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProxyHistoryRepository extends JpaRepository<HistoryPrice, Integer> {

    @SuppressWarnings("unchecked")
    HistoryPrice save (HistoryPrice historyPrice);

    List<HistoryPrice> getAllByIdPeriodical(int id);

    List<HistoryPrice> findAllByNamePeriodical(String namePeriodical);

    @Override
    List<HistoryPrice> findAll ();

}
