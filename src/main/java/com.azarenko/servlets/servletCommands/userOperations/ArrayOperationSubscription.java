package com.azarenko.servlets.servletCommands.userOperations;

import com.azarenko.dao.DaoException;
import com.azarenko.services.*;
import com.azarenko.servlets.servletCommands.CommandException;
import com.azarenko.util.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

public class ArrayOperationSubscription {
    private final Logger log = Logger.getLogger(ArrayOperationSubscription.class);

    private ConnectionPool connectionPool;

    public ArrayOperationSubscription() {
        try {
            connectionPool = ConnectionPool.getInstance();
        } catch (ServiceException e) {
            log.error(e);
        }
    }


    /**
     *
     * @param request
     * @param resp
     * @return
     */
    public String subscribe(HttpServletRequest request, HttpServletResponse resp) {
        String forward ="";
        return forward;
    }

    public String showPeriodicals(HttpServletRequest request, HttpServletResponse resp) {
        return "";
    }

}
