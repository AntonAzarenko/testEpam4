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
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import java.util.List;
import java.util.Set;

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
            if(namedParameterJdbcTemplate.update("UPDATE users SET name=:name, email=:email, password=: password," +
                    "enabled=: enabled, registration=:registration" ,maper) == 0);//todo
            return null;

        }
        return user;
    }

    @Override
    public User getByEmail(String email) {
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE email=?", map, email);//todo
        return setRoles(DataAccessUtils.singleResult(users));
    }

    @Override
    public int getIdByEmail(String email) {
       return 0;
    }

    @Override
    public List<User> getAll() {
        List<User> list = jdbcTemplate.query("SELECT * FROM users", map);//todo
        return list;
    }
    private User setRoles(User u) {
        if (u != null) {
            List<Role> roles = jdbcTemplate.query("SELECT role FROM roles  WHERE user_id=?",
                    (rs, rowNum) -> Role.valueOf(rs.getString("role")), u.getId());
            u.setAuthorities(roles);
        }
        return u;
    }
}
