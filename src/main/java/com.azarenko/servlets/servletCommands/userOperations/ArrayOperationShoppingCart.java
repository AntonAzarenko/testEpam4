package com.azarenko.servlets.servletCommands.userOperations;

import com.azarenko.dao.DaoException;
import com.azarenko.model.ShoppingCart;
import com.azarenko.services.*;
import com.azarenko.servlets.servletCommands.CommandException;
import com.azarenko.util.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class ArrayOperationShoppingCart {
    private final Logger log = Logger.getLogger(ArrayOperationShoppingCart.class);
    private final String CATALOG_LIST = "/user?action=start";
    private ConnectionPool connectionPool;

    public ArrayOperationShoppingCart() {
        try {
            connectionPool = ConnectionPool.getInstance();
        } catch (ServiceException e) {
            log.error(e);
        }
    }

    public String showShoppingCart(HttpServletRequest request, HttpServletResponse resp) {
        String forward = "";
        return forward;
    }

    public String add(HttpServletRequest request, HttpServletResponse resp) throws TransactionException {
        ShoppingCart shoppingCart = new ShoppingCart();
        //get User Id from data base with help login
        int userId = 0;
        HttpSession session = request.getSession();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            log.error(e);
        }
        ThreadLocal local = new ThreadLocal();
        local.set(connection);
        Transaction transaction = new TransactionImpl(local);
        ComponentRegister register = new ComponentRegister();
        try {
            transaction.start();
            UserService service = (UserService) register.getImpl(UserService.class);
            userId = service.getUserIdByEmail((String) session.getAttribute("login"));

        } catch (TransactionException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            transaction.rollback();
        }
        int periodicalId = 0;
        try {
             periodicalId = Integer.parseInt(request.getParameter("periodicalId"));
        } catch (NumberFormatException e) {

        }
        String count = request.getParameter("count");
        int countPer = 0;
        try{
            countPer = Integer.parseInt(count);
        } catch (NumberFormatException e){
            log.error(e);
        }
        shoppingCart.setUserID(userId);
        shoppingCart.setPeriodicalId(periodicalId);
        shoppingCart.setCountPer(countPer);
        ShoppingCartService service = (ShoppingCartService) register.getImpl(ShoppingCartService.class);
        service.add(shoppingCart);
        transaction.commit();
        connectionPool.returnConnection(connection);
        try {
            transaction.rollback();
        } catch (TransactionException e) {
            log.error(e);
        }
        return CATALOG_LIST;
    }
}
