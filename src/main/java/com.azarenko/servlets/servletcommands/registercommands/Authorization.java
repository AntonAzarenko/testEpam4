package com.azarenko.servlets.servletcommands.registercommands;

import com.azarenko.dao.DaoException;
import com.azarenko.services.AuthorisationService;
import com.azarenko.exceptions.ServiceException;
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
import java.sql.SQLException;

public class Authorization implements Command {
    private final String ADMIN = "/admin/?action=start";
    private final String USER = "/user?action=start";
    private final String START = "/pages/authorize.jsp";
    private final Logger log = Logger.getLogger(Authorization.class);

    @Override
    /**
     * This method gets login and password from parameter "request" and forwards them to checking. If user  is in a
     * database and password is confirmed, then this method gets parameter "role" and returns url depending of
     * the parameter "role"
     *
     * @param request
     * @param response
     * @return
     * @throws CommandException
     * @throws ServiceException
     * @Author Azarenko Anton
     */
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException {
        String forwardPage = "";
        ComponentRegister register = new ComponentRegister();
        Transaction transaction = (Transaction) register.getImpl(JdbcTransactionImpl.class);
        try {
            transaction.start();
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            if (login == null || login.isEmpty()) {
                request.setAttribute("error", "Введите логин");
                return START;
            } else if (password == null || password.isEmpty()) {
                request.setAttribute("error", "Введите пароль");
                return START;
            }
            String role = "";
            HttpSession session = request.getSession();
            AuthorisationService as = (AuthorisationService) register.getImpl(AuthorisationService.class);
            role = as.authorizeUser(login, password);
            if (role == null || role.isEmpty()) {
                request.setAttribute("error", "Неверный логин или пароль");
                return START;
            }
            switch (role) {
                case "ADMIN":
                    forwardPage = ADMIN;
                    session.setAttribute("role", role);
                    session.setAttribute("login", login);
                    break;
                case "USER":
                    forwardPage = USER;
                    session.setAttribute("role", role);
                    session.setAttribute("login", login);
                    break;
                default:
                    request.setAttribute("error", "Неверный логин или пароль");
                    forwardPage = START;
            }
            transaction.commit();
        } catch (TransactionException e) {
            log.error(e);
            throw new TransactionException(e);
        } catch (ServiceException e) {
            log.error(e);
            throw new ServiceException(e);
        } catch (DaoException e) {
            transaction.rollback();
            log.error(e);
        } finally {
            try {
                transaction.reliaseResources();
            } catch (SQLException e) {
                log.error(e);
            }
        }
        return forwardPage;

    }
}
