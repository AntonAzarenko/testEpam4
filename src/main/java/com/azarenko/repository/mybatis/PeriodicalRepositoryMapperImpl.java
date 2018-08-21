package com.azarenko.repository.mybatis;

import com.azarenko.model.Periodical;
import com.azarenko.repository.PeriodicalReposiroty;
import com.azarenko.repository.jdbc.PeriodicalRepositoryImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
@Repository
public class PeriodicalRepositoryMapperImpl implements PeriodicalReposiroty {
    private static final Logger log = LoggerFactory.getLogger(PeriodicalRepositoryImpl.class);


    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public Periodical save(Periodical entity) {
        return null;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }

    @Override
    public List<Periodical> getAll() {
        List<Periodical> list = sqlSessionFactory.openSession().selectList("com.azarenko.repository.PeriodicalRepository.getAll");
        list.forEach(p -> log.debug(p.toString()));
        return sqlSessionFactory.openSession().selectList("com.azarenko.repository.PeriodicalRepository.getAll");
    }

    @Override
    public Periodical get(int id) {
        return null;
    }

    @Override
    public Periodical search(BigDecimal price) {
        return null;
    }

    @Override
    public Periodical search(String title) {
        return null;
    }

    @Override
    public Periodical search(int index) {
        return null;
    }
}
