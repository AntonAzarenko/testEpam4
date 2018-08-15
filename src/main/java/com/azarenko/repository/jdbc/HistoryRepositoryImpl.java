package com.azarenko.repository.jdbc;

import com.azarenko.model.HistoryPrice;
import com.azarenko.repository.HistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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
public class HistoryRepositoryImpl implements HistoryRepository {

    private static final Logger LOG = LoggerFactory.getLogger(HistoryRepositoryImpl.class);

    private static final BeanPropertyRowMapper<HistoryPrice> mapper = new BeanPropertyRowMapper<HistoryPrice>() {
        @Nullable
        @Override
        public HistoryPrice mapRow(ResultSet resultSet, int i) throws SQLException {
            return new HistoryPrice(resultSet.getInt("id"), resultSet.getBigDecimal("price"),
                    resultSet.getString("name_periodicals"), resultSet.getInt("id_periodicals"),
                    resultSet.getDate("date_start"), resultSet.getDate("date_end"));
        }
    };

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public HistoryRepositoryImpl(DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("history_price")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<HistoryPrice> getAllByPeriodicalId(int id) {
        List<HistoryPrice> hp = jdbcTemplate.query(
                "SELECT * FROM mydb.history_price WHERE id_periodicals=?", mapper, id);
        return hp;
    }

    @Override
    public List<HistoryPrice> searchAllByName(String title) {
        List<HistoryPrice> hp = jdbcTemplate.query(
                "SELECT * FROM mydb.history_price WHERE name_periodicals=?", mapper, title);
        return hp;
    }

    @Override

    public void save(HistoryPrice historyPrice) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", historyPrice.getId())
                .addValue("id_periodicals", historyPrice.getIdPeriodical())
                .addValue("name_periodicals", historyPrice.getNamePeriodical())
                .addValue("date_start", historyPrice.getDateStart())
                .addValue("date_end", historyPrice.getDateEnd())
                .addValue("price", historyPrice.getPrice());
        if (historyPrice.getId() == null) {
            simpleJdbcInsert.execute(map);
            LOG.info("save status is OK");
        } else {
            if (namedParameterJdbcTemplate.update("UPDATE mydb.history_price SET id_periodicals:=id_periodicals," +
                    " name_periodicals=:name_periodicals, date_start=:date_start, date_end=:date_end," +
                    " price=:price WHERE id=:id", map) == 0) {
                LOG.info("Update status history price is Error");
            } else LOG.info("Update status history price  is Ok");

        }
    }

    @Override
    public List<HistoryPrice> getAll() {
        List<HistoryPrice> hp = jdbcTemplate.query(
                "SELECT * FROM mydb.history_price ", mapper);
        return hp;
    }



}
