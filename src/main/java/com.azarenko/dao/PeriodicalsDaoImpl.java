package com.azarenko.dao;

import com.azarenko.model.AbstractBaseEntity;
import com.azarenko.model.Periodicals;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeriodicalsDaoImpl extends BaseDaoImpl implements PeriodicalsDao {
    private final static Logger log = Logger.getLogger(PeriodicalsDaoImpl.class);

    public PeriodicalsDaoImpl() {

    }

    @Override
    public Periodicals getEntityById(int id) throws DaoException {
        Periodicals periodicals;
        Connection connection = getLocal().get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM mydb.catalog_periodicals WHERE id = ?")) {
            {
                preparedStatement.setInt(1, id);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    String title = rs.getString("title");
                    int oF = rs.getInt("output_frequency");
                    String discription = rs.getString("discription");
                    BigDecimal price = rs.getBigDecimal("price");
                    periodicals = new Periodicals(id, title, discription, oF, price);
                    return (Periodicals) periodicals;
                }
            }
            return null;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    @Override
    public void add(Periodicals entity) throws DaoException {
        Connection connection = getLocal().get();
        Periodicals periodicals = (Periodicals) entity;
        try (PreparedStatement preparedStatement = connection.prepareStatement
                ("INSERT INTO catalog_periodicals (title,output_frequency ,discription, price) VALUES (?,?,?,?)")) {
            preparedStatement.setString(1, periodicals.getTitle());
            preparedStatement.setInt(2, periodicals.getOutputFrequency());
            preparedStatement.setString(3, periodicals.getDescription());
            preparedStatement.setBigDecimal(4, periodicals.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    @Override
    public void remove(int id) throws DaoException {
        Connection connection = getLocal().get();
        try (PreparedStatement preparedStatement = connection.prepareStatement
                ("DELETE FROM mydb.catalog_periodicals WHERE id=?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }


    @Override
    public void update(Periodicals entity) throws DaoException {
        Connection connection = getLocal().get();
        Periodicals periodicals = (Periodicals) entity;
        try (PreparedStatement preparedStatement = connection.prepareStatement
                ("UPDATE mydb.catalog_periodicals SET title=?,discription=?,price=? WHERE id = ?")) {
            preparedStatement.setString(1, periodicals.getTitle());
            preparedStatement.setString(2, periodicals.getDescription());
            preparedStatement.setBigDecimal(3, periodicals.getPrice());
            preparedStatement.setInt(4, periodicals.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List getListEntity() throws DaoException {
        Connection connection = getLocal().get();
        List<Periodicals> list = new ArrayList<Periodicals>();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM mydb.catalog_periodicals")) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int outputFrequency = rs.getInt("output_frequency");
                String discription = rs.getString("discription");
                BigDecimal price = rs.getBigDecimal("price");
                Periodicals periodicals = new Periodicals(id, title, discription, outputFrequency, price);
                list.add(periodicals);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return list;
    }

}
