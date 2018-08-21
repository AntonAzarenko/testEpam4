package com.azarenko.repository.mybatis;

import com.azarenko.model.HistoryPrice;
import com.azarenko.repository.HistoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class HistoryRepositoryMapperImpl  implements HistoryRepository {
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
