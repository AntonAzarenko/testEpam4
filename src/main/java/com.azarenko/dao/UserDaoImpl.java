package com.azarenko.dao;

import com.azarenko.model.Users;
import com.azarenko.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private Connection connection;

    public UserDaoImpl() {
        this.connection = DBUtil.getConnection();
    }

    @Override
    public List<Users> getUserList() {
        List<Users> usersList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM mydb.users");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                Date date = rs.getDate("registered");
                boolean enabled = rs.getBoolean("enabled");
                String role = rs.getString("role");
                String subscription = rs.getString("subscriptions");
                Users user = new Users(id, name, email, password, enabled, role, subscription, date);
                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

}
