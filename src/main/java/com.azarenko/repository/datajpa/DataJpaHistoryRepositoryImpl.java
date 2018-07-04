package com.azarenko.repository.datajpa;

import com.azarenko.model.HistoryPrice;
import com.azarenko.repository.HistoryRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@Transactional(readOnly = true)
public class DataJpaHistoryRepositoryImpl implements HistoryRepository {
    @Override
    public List<HistoryPrice> getAllByPeriodicalId(int id) {
        return null;
    }

    @Override
    public List<HistoryPrice> searchAllByName(String name) {
        return null;
    }

    @Override
    public void save(HistoryPrice historyPrice) {
    }

    @Override
    public List<HistoryPrice> getAll() {
        return null;
    }
}
