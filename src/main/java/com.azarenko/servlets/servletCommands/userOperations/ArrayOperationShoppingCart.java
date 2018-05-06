package com.azarenko.servlets.servletCommands.userOperations;

import com.azarenko.dao.DaoException;
import com.azarenko.model.ShoppingCart;
import com.azarenko.services.*;
import com.azarenko.util.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ArrayOperationShoppingCart {
    private final Logger log = Logger.getLogger(ArrayOperationShoppingCart.class);
    private final String CATALOG_LIST = "/user?action=start";
    private final String SHOPPING_CART = "/pages/user/shopping_cart.jsp";
    private ConnectionPool connectionPool;

    public ArrayOperationShoppingCart() {
        try {
            connectionPool = ConnectionPool.getInstance();
        } catch (ServiceException e) {
            log.error(e);
        }
    }

    public String showShoppingCart(HttpServletRequest request, HttpServletResponse resp) throws TransactionException {
        String forward = SHOPPING_CART;
        ComponentRegister register = new ComponentRegister();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            log.error(e);
        }
        ThreadLocal<Connection> local = new ThreadLocal<>();
        local.set(connection);
        Transaction transaction = new JdbcTransactionImpl(local);
        try {
            transaction.start();
            ShoppingCartService serviceCart = (ShoppingCartService) register.getImpl(ShoppingCartService.class);
            HttpSession session = request.getSession();
            UserService userService = (UserService) register.getImpl(UserService.class);
            int userId = userService.getUserIdByEmail((String) session.getAttribute("login"));
            List<ShoppingCart> shoppingCarts = serviceCart.getShoppingCartUser(userId);
            request.setAttribute("cartList", shoppingCarts);
            BigDecimal fullPrice = serviceCart.getFullPriceForPayment(userId);
            request.setAttribute("FP",fullPrice);
            transaction.commit();
            connectionPool.returnConnection(connection);
        } catch (TransactionException e) {
            throw new TransactionException(e);
        } catch (DaoException e) {
            transaction.rollback();
            log.error(e);
        }

        return forward;
    }

    /**
     * Add object "shopping cart".
     *
     * @param request
     * @param resp
     * @return
     * @throws TransactionException
     */
    public String add(HttpServletRequest request, HttpServletResponse resp) throws TransactionException {
        ShoppingCart shoppingCart = new ShoppingCart();
        //get UserPage Id from data base with help login
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
        Transaction transaction = new JdbcTransactionImpl(local);
        ComponentRegister register = new ComponentRegister();
        try {
            transaction.start();
            UserService service = (UserService) register.getImpl(UserService.class);
            userId = service.getUserIdByEmail((String) session.getAttribute("login"));
        } catch (TransactionException e) {
            throw new TransactionException(e);
        } catch (DaoException e) {
            transaction.rollback();
            log.error(e);
        }
        int periodicalId = 0;
        try {
            periodicalId = Integer.parseInt(request.getParameter("periodicalId"));
        } catch (NumberFormatException e) {
            log.error(e);
        }
        String count = request.getParameter("count");
        int countPer = 0;
        try {
            countPer = Integer.parseInt(count);
        } catch (NumberFormatException e) {
            log.error(e);
        }
     /* //TODO
           String sDate = request.getParameter("dateStart");
        if (sDate.isEmpty() || sDate == null){
            shoppingCart.setStart(new Date());
        }else {
            shoppingCart.setStart(getDateToString(sDate));
        }*/

        shoppingCart.setUserID(userId);
        shoppingCart.setPeriodicalId(periodicalId);
        shoppingCart.setCountPer(countPer);
        ShoppingCartService service = (ShoppingCartService) register.getImpl(ShoppingCartService.class);
        try {
            service.add(shoppingCart);
            transaction.commit();
            connectionPool.returnConnection(connection);
        } catch (DaoException e) {
            transaction.rollback();
            log.error(e);
        }

        return CATALOG_LIST;
    }

    /**
     * Parse String to Date
     *
     * @param text
     * @return Date
     */
    private Date getDateToString(String text) {
        log.info(text);
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = format.parse(text);
        } catch (ParseException e) {
            log.error(e);
        }
        return date;
    }
}
