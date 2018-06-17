package com.azarenko.dao;

import com.azarenko.model.Periodical;
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
    public Periodical getEntityById(int id) throws DaoException {
        Periodical periodical;
        Connection connection = getConnection();
        ResultSet rs = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM mydb.catalog_periodicals WHERE id = ?")) {
            {
                preparedStatement.setInt(1, id);
                rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    String title = rs.getString("title");
                    int oF = rs.getInt("output_frequency");
                    String discription = rs.getString("discription");
                    BigDecimal price = rs.getBigDecimal("price");
                    periodical = new Periodical(id, title, discription, oF, price);
                    return (Periodical) periodical;
                }
            }
            return null;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    }

    @Override
    public void add(Periodical entity) throws DaoException {
        Connection connection = getConnection();
        Periodical periodical = (Periodical) entity;
        try (PreparedStatement preparedStatement = connection.prepareStatement
                ("INSERT INTO catalog_periodicals (title,output_frequency ,discription, price) VALUES (?,?,?,?)")) {
            preparedStatement.setString(1, periodical.getTitle());
            preparedStatement.setInt(2, periodical.getOutputFrequency());
            preparedStatement.setString(3, periodical.getDescription());
            preparedStatement.setBigDecimal(4, periodical.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void remove(int id) throws DaoException {
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement
                ("DELETE FROM mydb.catalog_periodicals WHERE id=?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public void update(Periodical entity) throws DaoException {
        Connection connection = getConnection();
        Periodical periodical =  entity;
        try (PreparedStatement preparedStatement = connection.prepareStatement
                ("UPDATE mydb.catalog_periodicals SET title=?,discription=?,output_frequency=?,price=? WHERE id = ?")) {
            preparedStatement.setString(1, periodical.getTitle());
            preparedStatement.setString(2, periodical.getDescription());
            preparedStatement.setBigDecimal(3, periodical.getPrice());
            preparedStatement.setInt(4, periodical.getOutputFrequency());
            preparedStatement.setInt(5, periodical.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List getListEntity() throws DaoException {
        Connection connection = getConnection();
        List<Periodical> list = new ArrayList<Periodical>();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM mydb.catalog_periodicals")) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int outputFrequency = rs.getInt("output_frequency");
                String discription = rs.getString("discription");
                BigDecimal price = rs.getBigDecimal("price");
                Periodical periodical = new Periodical(id, title, discription, outputFrequency, price);
                list.add(periodical);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return list;
    }

    @Override
    public Periodical search(int value) throws DaoException {
        return getEntityById(value);
    }

    @Override
    public Periodical search(BigDecimal value) throws DaoException {
        Periodical periodical = null;
        Connection connection = getConnection();
        ResultSet rs = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement
                ("SELECT *FROM mydb.catalog_periodicals WHERE price = ?")) {
            preparedStatement.setBigDecimal(1, value);
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int oF = rs.getInt("output_frequency");
                String discription = rs.getString("discription");
                BigDecimal price = rs.getBigDecimal("price");
                periodical = new Periodical(id, title, discription, oF, price);
                log.info(periodical.getTitle());
                return  periodical;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return null;
    }

    @Override
    public Periodical search(String value) throws DaoException {
        Periodical periodical = null;
        Connection connection = getConnection();
        ResultSet rs = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement
                ("SELECT *FROM mydb.catalog_periodicals WHERE title = ?")) {
            preparedStatement.setString(1, value);
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                String title = rs.getString("title");
                int id = rs.getInt("id");
                int oF = rs.getInt("output_frequency");
                String discriptions = rs.getString("discription");
                BigDecimal price = rs.getBigDecimal("price");
                periodical = new Periodical(id, title, discriptions, oF, price);
                log.info(value);
                return  periodical;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return null;
    }

}
