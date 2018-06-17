package com.azarenko.repository;


import com.azarenko.model.Periodical;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PeriodicalRepositoryImpl implements PeriodicalReposiroty {
    private static final Logger LOG = LoggerFactory.getLogger(PeriodicalRepositoryImpl.class);

    private static final BeanPropertyRowMapper<Periodical> mapper = new BeanPropertyRowMapper<Periodical>() {
        @Nullable
        @Override
        public Periodical mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Periodical(resultSet.getInt("id"), resultSet.getString("title"),
                    resultSet.getString("dicription"), resultSet.getInt("output_frequency"),
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
    public Periodical add(Periodical periodical, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", periodical.getId())
                .addValue("title", periodical.getTitle())
                .addValue("output_frequency", periodical.getOutputFrequency())
                .addValue("discription", periodical.getDescription())
                .addValue("price", periodical.getPrice());
        if (periodical.isNew()) {
            Number newId = simpleJdbcInsert.executeAndReturnKey(map);
            periodical.setId(newId.intValue());
            LOG.info("add is OK");
        } else {
            if (namedParameterJdbcTemplate.update(
                    "UPDATE mydb.catalog_periodicals SET title=:title," +
                            "discription=:discription, output_frequency=:output_frequency ,price=:price WHERE id=:id", map) == 0) {
                return null;
            }
        }
        return periodical;
    }


    @Override
    public void remove(int id) {

    }

    @Override
    public void update(Periodical entity) {

    }

    @Override
    public List<Periodical> getListEntity() {
        return null;
    }

    @Override
    public Periodical get(int id, int userId) {
        return null;
    }

    @Override
    public Periodical get(int id) {
        return null;
    }
}
