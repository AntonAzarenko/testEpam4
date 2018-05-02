package com.azarenko.servlets.servletCommands.userOperations;

import com.azarenko.dao.DaoException;
import com.azarenko.model.Subscription;
import com.azarenko.services.*;
import com.azarenko.util.ComponentRegister;
import com.azarenko.util.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ArrayOperationUser {
    private final Logger log = Logger.getLogger(ArrayOperationUser.class);
    private final String START = "/pages/start_user.jsp";
    private final String PROFILE = "/pages/user/profile.jsp";
    private ConnectionPool connectionPool;

    public ArrayOperationUser() {
        try {
            connectionPool = ConnectionPool.getInstance();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public String getUser(HttpServletRequest request, HttpServletResponse resp) {
        String forward = "";
        return forward;
    }

    public String userExit(HttpServletRequest request, HttpServletResponse resp) {
        return "";
    }

    public String forwardStart(HttpServletRequest request, HttpServletResponse resp) {
        return START;
    }

    public String profile(HttpServletRequest request, HttpServletResponse resp) throws TransactionException {
        Connection connection = null;
        HttpSession session = request.getSession();
        ComponentRegister register = new ComponentRegister();
        try {
            connection = connectionPool.getConnection();
            ThreadLocal<Connection> local = new ThreadLocal<>();
            local.set(connection);
            Transaction transaction = new TransactionImpl(local);
            transaction.start();
            SubscriptionService service = (SubscriptionService) register.getImpl(SubscriptionService.class);
            UserService userService = (UserService) register.getImpl(UserService.class);
            int userId = userService.getUserIdByEmail((String) session.getAttribute("login"));
            List<Subscription> subscriptionList = service.getAllSubscriptionsUserByUserId(userId);
            request.setAttribute("subList", subscriptionList);
            transaction.commit();
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            log.fatal(e);
        } catch (TransactionException e) {
            throw new TransactionException(e);
        } catch (DaoException e) {

        }

        return PROFILE;
    }
}
