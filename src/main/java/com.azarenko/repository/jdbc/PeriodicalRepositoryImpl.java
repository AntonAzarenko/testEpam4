package com.azarenko.repository.jdbc;


import com.azarenko.model.Periodical;
import com.azarenko.repository.PeriodicalReposiroty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 */
@Repository
@PersistenceContext(type = PersistenceContextType.EXTENDED)
public class PeriodicalRepositoryImpl implements PeriodicalReposiroty {
    private static final Logger LOG = LoggerFactory.getLogger(PeriodicalRepositoryImpl.class);

    private static final BeanPropertyRowMapper<Periodical> mapper = new BeanPropertyRowMapper<Periodical>() {
        @Nullable
        @Override
        public Periodical mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Periodical(resultSet.getInt("id"), resultSet.getString("title"),
                    resultSet.getString("discription"), resultSet.getString("publisher"), resultSet.getInt("output_frequency"),
                    resultSet.getInt("per_index"), resultSet.getInt("age_limit"),
                    resultSet.getBigDecimal("price"));
        }
    };

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;


    @Autowired
    public PeriodicalRepositoryImpl(DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("catalog_periodicals")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Periodical save(Periodical periodical) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", periodical.getId())
                .addValue("title", periodical.getTitle())
                .addValue("output_frequency", periodical.getOutputFrequency())
                .addValue("discription", periodical.getDiscription())
                .addValue("publisher", periodical.getPublisher())
                .addValue("per_index", periodical.getIndex())
                .addValue("age_limit", periodical.getAgeLimit())
                .addValue("price", periodical.getPrice());
        if (periodical.isNew()) {
            Number newId = simpleJdbcInsert.executeAndReturnKey(map);
            periodical.setId(newId.intValue());
            LOG.info("Save status is - OK " + periodical.getId());
        } else {
            if (namedParameterJdbcTemplate.update(
                    "UPDATE mydb.catalog_periodicals SET title=:title," +
                            "discription=:discription, publisher=:publisher, output_frequency=:output_frequency ,price=:price," +
                            "per_index=:per_index, age_limit=:age_limit WHERE id=:id", map) == 0) {
                return null;
            } else {
                LOG.info("Update status is - OK");
            }
        }
        return periodical;
    }

    @Override
    public boolean remove(int id) {
        return jdbcTemplate.update(
                "DELETE FROM catalog_periodicals WHERE id=?", id) != 0;
    }

    @Override
    public List<Periodical> getAll() {
        return jdbcTemplate.query("SELECT * FROM catalog_periodicals", mapper);
    }


    @Override
    public Periodical get(int id) {
        List<Periodical> periodical = jdbcTemplate.query(
                "SELECT * FROM catalog_periodicals WHERE id=?", mapper, id);
        LOG.info("get status ok");
        return DataAccessUtils.singleResult(periodical);
    }

    @Override
    public Periodical search(BigDecimal price) {
        List<Periodical> periodical = jdbcTemplate.query(
                "SELECT * FROM catalog_periodicals WHERE price=?", mapper, price);
        return DataAccessUtils.singleResult(periodical);
    }

    @Override
    public Periodical search(String title) {
        List<Periodical> periodical = jdbcTemplate.query(
                "SELECT * FROM catalog_periodicals WHERE title=?", mapper, title);
        return DataAccessUtils.singleResult(periodical);
    }

    @Override
    public Periodical search(int index) {
        List<Periodical> periodical = jdbcTemplate.query(
                "SELECT * FROM catalog_periodicals WHERE per_index=?", mapper, index);
        return DataAccessUtils.singleResult(periodical);
    }
}
