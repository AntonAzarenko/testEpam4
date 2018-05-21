package com.azarenko.servlets.servletcommands.admincommands;

import com.azarenko.dao.DaoException;
import com.azarenko.model.User;
import com.azarenko.services.ServiceException;
import com.azarenko.services.UserService;
import com.azarenko.servlets.servletcommands.Command;
import com.azarenko.servlets.servletcommands.CommandException;
import com.azarenko.util.ComponentRegister;
import com.azarenko.util.JdbcTransactionImpl;
import com.azarenko.util.PagedListHolder;
import com.azarenko.util.Transaction;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

public class ShowUsers implements Command {

    private final static Logger log = Logger.getLogger(ShowUsers.class);
    private final String SHOW_USER = "/pages/admin/show_users.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException, UnsupportedEncodingException, SQLException {
        ComponentRegister register = new ComponentRegister();
        UserService service = (UserService) register.getImpl(UserService.class);
        Transaction transaction = (Transaction) register.getImpl(JdbcTransactionImpl.class);
        try {
            log.info("startTransaction");
            transaction.start();
            log.info("loading...Transaction");
            List<User> userList = service.getAllUsers();
            log.info("getAllUsers");
            PagedListHolder<User> listHolder = new PagedListHolder<>(userList);
            listHolder.setNameList("userList");
            listHolder.setPageSize(8);
            listHolder.setPadding(request);
            log.info("sendReqlist");
            transaction.commit();
            log.info("trcommit");
        } catch (DaoException e) {
            transaction.rollback();
            log.fatal(e);
            request.setAttribute("error", "Танзакция закончилась неудачей");
        }finally {
            transaction.reliaseResources();
        }
        return SHOW_USER;
    }
}
