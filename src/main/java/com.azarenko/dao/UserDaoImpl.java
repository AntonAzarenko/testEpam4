package com.azarenko.dao;

import com.azarenko.model.AbstractBaseEntity;
import com.azarenko.model.User;
import com.azarenko.util.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    private static final Logger log = Logger.getLogger(UserDaoImpl.class);

    @Override
    public User getUserByEmail(String email) throws DaoException {
        try {
            Connection connection = getLocal().get();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM mydb.users");
            while (rs.next()) {
                String temp = rs.getString("email");
                if (email.equalsIgnoreCase(temp)) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String password = rs.getString("password");
                    String role = rs.getString("role");
                    boolean enabled = false;
                    int en = rs.getInt("enabled");
                    Date date = rs.getDate("registration");
                    if (en == 1) {
                        enabled = true;
                    }
                    //создаем  User'а используя патрн BUILDER
                    User.UserBulder userBulder = new User.UserBulder(id);
                    userBulder.email(email);
                    userBulder.name(name);
                    userBulder.enabled(enabled);
                    userBulder.password(password);
                    userBulder.registered(date);
                    userBulder.role(role);
                    User user = userBulder.build();
                    return user;

                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return null;
    }

    @Override
    public int getUserIdByEmail(String login) throws DaoException {
        User user = getUserByEmail(login);
        return user.getId();
    }

    @Override
    public User getEntityById(int id) {
        User user = null;
        Connection connection = getLocal().get();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM mydb.users WHERE id(?)");) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                User.UserBulder userBulder = new User.UserBulder(rs.getInt("id"));
                userBulder.name(rs.getString("name"));
                userBulder.email(rs.getString("email"));
                userBulder.password(rs.getString("password"));
                userBulder.role(rs.getString("role"));
                userBulder.registered(rs.getDate("registration"));
                boolean enabled = false;
                int en = rs.getInt("enabled");
                if (en == 1) {
                    enabled = true;
                }
                userBulder.enabled(enabled);
                user = userBulder.build();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return user;
    }

    @Override
    public void add(User entity) throws DaoException {
        User user = (User) entity;
        Connection connection = getLocal().get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT  INTO mydb.users(name,email,password,enabled,role) VALUES (?,?,?,?,?)")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, 1);
            preparedStatement.setString(5, "USER");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public List getListEntity() throws DaoException {
        List<User> userList = new ArrayList<>();
        Connection connection = getLocal().get();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM mydb.users");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                Date date = rs.getDate("registration");
                int en = rs.getInt("enabled");
                boolean enabled = false;
                if (en == 1) {
                    enabled = true;
                }
                String role = rs.getString("role");
                User.UserBulder userBulder = new User.UserBulder(id);
                userBulder.email(email);
                userBulder.name(name);
                userBulder.enabled(enabled);
                userBulder.password(password);
                userBulder.registered(date);
                userBulder.role(role);
                User user = userBulder.build();
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return userList;
    }

}
