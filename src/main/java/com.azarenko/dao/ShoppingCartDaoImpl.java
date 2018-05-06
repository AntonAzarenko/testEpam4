package com.azarenko.dao;

import com.azarenko.model.ShoppingCart;
import com.azarenko.util.DBUtil;
import org.apache.log4j.Logger;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDaoImpl implements ShoppingCartDao {
    private static final Logger log = Logger.getLogger(ShoppingCartDaoImpl.class);

    private Connection connection;

    public ShoppingCartDaoImpl() {
        connection = DBUtil.getConnection();
    }

    @Override
    public void add(ShoppingCart entity) throws DaoException {
        ShoppingCart shoppingCart = (ShoppingCart) entity;
        try (PreparedStatement preparedStatement = connection.prepareStatement
                ("INSERT INTO shopping_cart(userId, id_periodicals, date_start, date_end,countPer, price )VALUES (?,?,?,?,?,?)")) {
            log.info(shoppingCart.getUserID());
            preparedStatement.setInt(1, shoppingCart.getUserID());
            preparedStatement.setInt(2, shoppingCart.getPeriodicalId());
            log.info(shoppingCart.getStart());
            java.util.Date dateStart = shoppingCart.getStart();
            java.util.Date dateEnd = shoppingCart.getEnd();
            preparedStatement.setDate(3, new Date(dateStart.getTime()));
            preparedStatement.setDate(4, new Date(dateEnd.getTime()));
            preparedStatement.setInt(5,shoppingCart.getCountPer());
            preparedStatement.setBigDecimal(6, shoppingCart.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void update(ShoppingCart entity) {

    }

    @Override
    public List getListEntity() {
        return null;
    }

    @Override
    public List<ShoppingCart> getShoppingCartByUserId(int id) throws DaoException {
        List<ShoppingCart> cartList = new ArrayList<>();
        try (Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM mydb.shopping_cart")) {
            while (rs.next()) {
                int usId = rs.getInt("userId");
                if (usId == id) {
                    ShoppingCart shoppingCart = new ShoppingCart();
                    shoppingCart.setId(rs.getInt("id"));
                    shoppingCart.setUserID(rs.getInt("userId"));
                    shoppingCart.setPeriodicalId(rs.getInt("id_periodicals"));
                    shoppingCart.setStart(rs.getDate("date_start"));
                    shoppingCart.setEnd(rs.getDate("date_end"));
                    shoppingCart.setPrice(rs.getBigDecimal("price"));
                    cartList.add(shoppingCart);
                }
            }
        } catch (SQLException e) {
            throw  new DaoException(e);
        }
        return cartList;
    }

    @Override
    public void removeShoppingCartUser(int id) throws DaoException {
        try(PreparedStatement preparedStatement = connection.prepareStatement
                ("DELETE  FROM mydb.shopping_cart WHERE userId=?")){
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
           throw new DaoException(e);
        }
    }

    @Override
    public ShoppingCart getEntityById(int id) {
        return null;
    }
}
