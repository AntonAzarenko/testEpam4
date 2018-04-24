package com.azarenko.servlets.servletCommands.adminOperations;

import com.azarenko.dao.DaoException;
import com.azarenko.services.*;
import com.azarenko.servlets.servletCommands.CommandException;
import com.azarenko.util.ConnectionPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

public class ArrayOperationPayment {
    private final String PAYMENT = "/pages/admin/payment.jsp";

    public ArrayOperationPayment() {
        pool = ConnectionPool.getInstance();
    }

    private ConnectionPool pool;

    public String showPayments(HttpServletRequest request, HttpServletResponse response) throws CommandException, ServiceException {
        String forward = "";
        Connection connection = null;
        try {
            connection = pool.getConnection();
        } catch (SQLException e) {
            throw new CommandException(e);
        }
        ThreadLocal<Connection> local = new ThreadLocal<>();
        local.set(connection);
        Transaction transaction = new TransactionImpl(local);
        try {
            transaction.start();
            PaymentService service = new PaymentServiceImpl();
            request.setAttribute("paymentList", service.getPaymentList());
            transaction.commit();
            forward = PAYMENT;
            pool.returnConnection(connection);
        } catch (TransactionException e) {
            throw new TransactionException(e);
        } catch (DaoException e) {
            transaction.rollback();
            throw new TransactionException(e);
        } catch (ServiceException e) {
            throw  new ServiceException(e);
        }
        return forward;
    }
}
