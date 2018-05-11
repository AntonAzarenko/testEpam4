package com.azarenko.servlets.servletcommands.usercommands;

import com.azarenko.services.ServiceException;
import com.azarenko.servlets.servletcommands.Command;
import com.azarenko.servlets.servletcommands.CommandException;
import com.azarenko.util.ComponentRegister;
import com.azarenko.util.JdbcTransactionImpl;
import com.azarenko.util.Transaction;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public class Search implements Command {
    private final Logger log = Logger.getLogger(Search.class);
    private final String CATALOG_LIST = "/pages/user/user_catalog.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException, UnsupportedEncodingException, SQLException {
        String entitySearch = request.getParameter("entitySearch");
        String value = request.getParameter("value");
        ComponentRegister register = new ComponentRegister();
        Transaction transaction = (Transaction) register.getImpl(JdbcTransactionImpl.class);
        if(entitySearch.equals("name")){

        }
        return CATALOG_LIST;
    }
}
