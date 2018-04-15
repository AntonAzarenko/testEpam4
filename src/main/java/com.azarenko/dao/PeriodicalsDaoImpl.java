package com.azarenko.dao;

import com.azarenko.model.AbstractBaseEntity;
import com.azarenko.model.Periodicals;
import com.azarenko.util.DBUtil;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeriodicalsDaoImpl implements PeriodicalsDao {

    private final static Logger log = Logger.getLogger(PeriodicalsDaoImpl.class);

    private Connection connection;

    public PeriodicalsDaoImpl() {
        connection = DBUtil.getConnection();
    }

    public List<Periodicals> getCatalog() {
        List<Periodicals> temp = new ArrayList<Periodicals>();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM mydb.catalog_periodicals")) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int outputFrequency = rs.getInt("output_frequency");
                String discription = rs.getString("discription");
                BigDecimal price = rs.getBigDecimal("price");
                Periodicals periodicals = new Periodicals(id, title, discription, outputFrequency, price);
                temp.add(periodicals);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("return catalog");
        return temp;
    }

    @Override
    public void add(Periodicals periodicals) {
        try (PreparedStatement preparedStatement = connection.prepareStatement
                ("INSERT INTO catalog_periodicals (title,discription, price) VALUES (?,?,?)")

        ) {
            preparedStatement.setString(1, periodicals.getTitle());
            preparedStatement.setString(2, periodicals.getDescription());
            preparedStatement.setBigDecimal(3, periodicals.getPrice());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public void remove(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement
                ("DELETE FROM mydb.catalog_periodicals WHERE id=?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Periodicals periodicals) {
        try (PreparedStatement preparedStatement = connection.prepareStatement
                ("UPDATE mydb.catalog_periodicals SET title=?,discription=?,price=? WHERE id = ?")) {
            preparedStatement.setString(1, periodicals.getTitle());
            preparedStatement.setString(2, periodicals.getDescription());
            preparedStatement.setBigDecimal(3, periodicals.getPrice());
            preparedStatement.setInt(4, periodicals.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Periodicals getPeriodicalById(int id) {
        AbstractBaseEntity periodicals;
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM mydb.catalog_periodicals")) {
            while (rs.next()) {
                if (rs.getInt("id") == id) {
                    String title = rs.getString("title");
                    int oF = rs.getInt("output_frequency");
                    String discription = rs.getString("discription");
                    BigDecimal price = rs.getBigDecimal("price");
                    periodicals = new Periodicals(id, title, discription, oF, price);
                    return (Periodicals) periodicals;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
