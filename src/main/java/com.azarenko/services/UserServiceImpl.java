package com.azarenko.services;

import com.azarenko.model.User;
import com.azarenko.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {

    private Connection connection;

    public UserServiceImpl() {
        this.connection = DBUtil.getConnection();
    }

    @Override
    public List<User> getUserList() {
        List<User> userList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM mydb.users");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                Date date = rs.getDate("registered");
                int en = rs.getInt("enabled");
                boolean enabled = false;
                if (en == 1) {
                    enabled = true;
                }
                String role = rs.getString("role");
                String subscription = rs.getString("subscriptions");
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
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User getUserByEmail(String email) {

        try {
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
                //создаем  User'а используя патрн BULDER
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
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserByID(int id) {
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM mydb.users WHERE id(?)");
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

}
