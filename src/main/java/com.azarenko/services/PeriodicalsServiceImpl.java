package com.azarenko.services;

import com.azarenko.model.Periodicals;
import com.azarenko.util.DBUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeriodicalsServiceImpl implements PeriodicalsService {

    private Connection connection;

    public PeriodicalsServiceImpl() {
        connection = DBUtil.getConnection();
    }

    public List<Periodicals> getCatalog() {
        List<Periodicals> temp = new ArrayList<Periodicals>();
        try {

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM mydb.catalog_periodicals");
            while (rs.next()) {
                int id = rs.getInt("idpublications");
                String title = rs.getString("title");
                String discription = rs.getString("discription");
                BigDecimal price = rs.getBigDecimal("price");
                Periodicals periodicals = new Periodicals(id, title, discription, price);
                temp.add(periodicals);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }

    @Override
    public void add(Periodicals periodicals) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("INSERT INTO catalog_periodicals (title,discription, price) VALUES (?,?,?)");
            preparedStatement.setString(1, periodicals.getTitle());
            preparedStatement.setString(2, periodicals.getDescription());
            preparedStatement.setBigDecimal(3, periodicals.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void update(Periodicals periodicals) {

    }

    @Override
    public Periodicals getPublication(int id) {
        return null;
    }
}
