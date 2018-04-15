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
    public void add(ShoppingCart shoppingCart) {
        try (PreparedStatement preparedStatement = connection.prepareStatement
                ("INSERT INTO shopping_cart(userId, id_periodicals, date_start, date_end )VALUES (?,?,?,?)")) {
            preparedStatement.setInt(1, shoppingCart.getUserID());
            preparedStatement.setInt(2, shoppingCart.getPeriodicalId());
            java.util.Date dateStart = shoppingCart.getStart();
            java.util.Date dateend = shoppingCart.getEnd();
            preparedStatement.setDate(3, new Date(dateStart.getTime()));
            preparedStatement.setDate(4, new Date(dateend.getTime()));
            preparedStatement.executeUpdate();
            log.info("write to table");
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public List<ShoppingCart> getShoppingCartByUserId(int id) {
        List<ShoppingCart> cartList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM mydb.shopping_cart WHERE userId = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                ShoppingCart shoppingCart = new ShoppingCart();
                shoppingCart.setId(rs.getInt("id"));
                shoppingCart.setUserID(rs.getInt("userId"));
                shoppingCart.setPeriodicalId(rs.getInt("id_periodicals"));
                shoppingCart.setStart(rs.getDate("date_start"));
                shoppingCart.setEnd(rs.getDate("date_end"));
                cartList.add(shoppingCart);
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return cartList;
    }
}
