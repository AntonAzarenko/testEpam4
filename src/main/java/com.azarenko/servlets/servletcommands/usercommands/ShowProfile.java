package com.azarenko.servlets.servletcommands.usercommands;

import com.azarenko.dao.DaoException;
import com.azarenko.model.User;
import com.azarenko.exceptions.ServiceException;
import com.azarenko.services.UserService;
import com.azarenko.servlets.servletcommands.Command;
import com.azarenko.servlets.servletcommands.CommandException;
import com.azarenko.util.ComponentRegister;
import com.azarenko.util.JdbcTransactionImpl;
import com.azarenko.util.Transaction;
import com.azarenko.util.TransactionException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public class ShowProfile implements Command {
    private final Logger log = Logger.getLogger(ShowProfile.class);
    private final String MY_PROFILE = "/pages/user/my_profile.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException, UnsupportedEncodingException {
        ComponentRegister register = new ComponentRegister();
        HttpSession session = request.getSession();

        Transaction transaction = (Transaction) register.getImpl(JdbcTransactionImpl.class);
        try {
            transaction.start();
            UserService service = (UserService) register.getImpl(UserService.class);
            User user = service.getUserByEmail((String) session.getAttribute("login"));
            request.setAttribute("user", user);
            transaction.commit();
        } catch (TransactionException e) {
            throw new TransactionException(e);
        } catch (DaoException e) {
            transaction.rollback();
            log.error(e);
            request.setAttribute("error", "Транцакция закончилась неудачей");
        } finally {
            try {
                transaction.reliaseResources();
            } catch (SQLException e) {
                log.error(e);
            }
        }
        return MY_PROFILE;
    }
}
