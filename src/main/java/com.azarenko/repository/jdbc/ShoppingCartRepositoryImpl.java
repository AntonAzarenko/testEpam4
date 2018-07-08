package com.azarenko.repository.jdbc;

import com.azarenko.model.ShoppingCart;
import com.azarenko.repository.ShoppingCartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ShoppingCartRepositoryImpl implements ShoppingCartRepository {

    private static final Logger LOG = LoggerFactory.getLogger(ShoppingCartRepositoryImpl.class);

    private BeanPropertyRowMapper<ShoppingCart> mapper = new BeanPropertyRowMapper<ShoppingCart>() {
        public ShoppingCart rowMapper(ResultSet rs, int i) throws SQLException {
            return new ShoppingCart(rs.getInt("id"), rs.getInt("userId"),
                    rs.getInt("id_periodicals"), rs.getDate("date_start"),
                    rs.getDate("date_end"), rs.getBigDecimal("price"),
                    rs.getInt("countPer"));
        }
    };

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;

    public ShoppingCartRepositoryImpl(DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("shopping_cart")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public void add(ShoppingCart shoppingCart) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", shoppingCart.getId())
                .addValue("userId", shoppingCart.getUserID())
                .addValue("id_periodicals", shoppingCart.getPeriodicalId())
                .addValue("date_sart", shoppingCart.getStart())
                .addValue("date_end", shoppingCart.getEnd())
                .addValue("countPer", shoppingCart.getCountPer())
                .addValue("price", shoppingCart.getPrice());
        if (shoppingCart.getId() != null) {
            Number id = simpleJdbcInsert.executeAndReturnKey(map);
            shoppingCart.setId(id.intValue());
        } else {
            shoppingCart.setCountPer(shoppingCart.getCountPer() + 1);
            if (namedParameterJdbcTemplate.update("UPDATE mydb.shopping_cart SET userId=:userId, " +
                    "id_periodicals=:id_periodicals, date_start=:date_start, date_end=:date_end," +
                    "countPer=:countPer, price=:price WHERE id=:id", map) == 0) {
                LOG.error("update shopping cart status - error");
            } else {
                LOG.info("update shopping cart status - OK");
            }
        }
    }

    @Override
    public List<ShoppingCart> getAllByUserID(int id) {
        return jdbcTemplate.query("SELECT * FROM mydb.shopping_cart WHERE userId = ?", mapper, id);
    }

    @Override
    public boolean removeByUserId(int id) {
        return jdbcTemplate.update("DELETE * FROM mydb.shopping_cart WHERE id=?", mapper, id) != 0;
    }

    @Override//TODO
    public ShoppingCart getByPeriodicalID(int id) {
        return null;
    }
}