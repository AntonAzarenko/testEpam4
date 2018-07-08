package com.azarenko.repository.datajpa;

import com.azarenko.model.HistoryPrice;
import com.azarenko.repository.HistoryRepository;
import com.azarenko.repository.datajpa.proxy.ProxyHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@Transactional(readOnly = true)
public class DataJpaHistoryRepositoryImpl implements HistoryRepository {

    private ProxyHistoryRepository proxys;

    @Override
    public List<HistoryPrice> getAllByPeriodicalId(int id) {
        return proxys.getAllByIdPeriodical(id);
    }

    @Override
    public List<HistoryPrice> searchAllByName(String name) {
        return proxys.findAllByNamePeriodical(name);
    }

    @Override
    @Transactional
    public void save(HistoryPrice historyPrice) {
        proxys.save(historyPrice);
    }

    @Override
    public List<HistoryPrice> getAll() {
        return proxys.findAll();
    }
}
