package com.azarenko.repository.jdbc;

import com.azarenko.model.Role;
import com.azarenko.model.User;
import com.azarenko.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    public static final BeanPropertyRowMapper<User> map = BeanPropertyRowMapper.newInstance(User.class);
    private static final Logger LOG = LoggerFactory.getLogger(UserRepositoryImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;

    public UserRepositoryImpl(DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public User add(User user) {
        MapSqlParameterSource maper = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("name", user.getName())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("enabled" , user.isEnabled())
                .addValue("registration", user.getRegistered());
        if(user.isNew()){
            jdbcTemplate.update("",map);//todo
            Number id = simpleJdbcInsert.executeAndReturnKey(maper);
            user.setId(id.intValue());
        }else{
            if(namedParameterJdbcTemplate.update("",maper) == 0);//todo
        }
        return null;
    }

    @Override
    public User getByEmail(String email) {
        List<User> list = jdbcTemplate.query("", map);//todo
        return DataAccessUtils.singleResult(list);
    }

    @Override
    public int getIdByEmail(String login) {
        List<User> list = jdbcTemplate.query("", map);//todo
        return DataAccessUtils.singleResult(list).getId();
    }

    @Override
    public List<User> getAll() {
        List<User> list = jdbcTemplate.query("", map);//todo
        return list;
    }
}
