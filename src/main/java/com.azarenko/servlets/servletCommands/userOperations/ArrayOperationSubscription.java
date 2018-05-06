package com.azarenko.servlets.servletCommands.userOperations;

import com.azarenko.dao.DaoException;
import com.azarenko.model.Payment;
import com.azarenko.services.*;
import com.azarenko.util.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class ArrayOperationSubscription {
    private final Logger log = Logger.getLogger(ArrayOperationSubscription.class);
    private final String START = "/user?action=start";


    private ConnectionPool connectionPool;

    public ArrayOperationSubscription() {
        try {
            connectionPool = ConnectionPool.getInstance();
        } catch (ServiceException e) {
            log.error(e);
        }
    }


    /**
     * Pay
     *
     * @param request
     * @param resp
     * @return
     */
    public String pay(HttpServletRequest request, HttpServletResponse resp) throws TransactionException {
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
        HttpSession session = request.getSession();
        int userId = 0;
        try {
            transaction.start();
            UserService userService = (UserService) register.getImpl(UserService.class);
            userId = userService.getUserIdByEmail((String) session.getAttribute("login"));
            ShoppingCartService cartService = (ShoppingCartService) register.getImpl(ShoppingCartService.class);
            BigDecimal price = cartService.getFullPriceForPayment(userId);
            if(price==null){
                transaction.rollback();
                connectionPool.returnConnection(connection);
                return START;
            }
            Payment payment = getNewPayment(userId, price);
            PaymentService service = (PaymentService) register.getImpl(PaymentService.class);
            service.add(payment);
            transaction.commit();
            cartService.removeShoppingCartUser(userId);
            connectionPool.returnConnection(connection);
        } catch (TransactionException e) {
            throw new TransactionException(e);
        } catch (DaoException e) {
            transaction.rollback();
            log.error(e);
        } catch (ServiceException e) {
            log.error(e);
        }
        String forward = START;
        return forward;
    }

    private Payment getNewPayment(int userId, BigDecimal price) {
        Payment.PaymentBuilder paymentBuilder = new Payment.PaymentBuilder();
        paymentBuilder.date(new Date());
        paymentBuilder.price(price);
        paymentBuilder.userId(userId);
        return paymentBuilder.build();
    }

    public String showPeriodicals(HttpServletRequest request, HttpServletResponse resp) {
        return "";
    }

}
