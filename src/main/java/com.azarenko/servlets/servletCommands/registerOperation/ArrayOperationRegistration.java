package com.azarenko.servlets.servletCommands.registerOperation;

import com.azarenko.dao.DaoException;
import com.azarenko.services.*;
import com.azarenko.servlets.servletCommands.CommandException;
import com.azarenko.servlets.servletCommands.adminOperations.ArrayOperationPeriodical;
import com.azarenko.util.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;

public class ArrayOperationRegistration {
    private final String REGISTERED = "/pages/user/registration.jsp";
    private final String ERROR = "/pages/info/erors_message.jsp";
    private final String ADMIN = "pages/admin/admin_start_page.jsp";
    private final String USER = "user?action=catalog";
    private final Logger log = Logger.getLogger(ArrayOperationPeriodical.class);

    private ConnectionPool connectionPool;

    public ArrayOperationRegistration() {
      connectionPool = ConnectionPool.getInstance();
    }

    /**
     * this method redirects to rhe page "registration"
     * @param request
     * @param resp
     * @return
     */
    public String registrationRedirect(HttpServletRequest request, HttpServletResponse resp) {
        String forward = REGISTERED;
        return forward;
    }

    /**
     *
     * @param request
     * @param response
     * @return
     */
    public String registration(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        //Check data on valid
        if(name.isEmpty()||name == null){
            request.setAttribute("error1","Введите имя");
            return REGISTERED;
        }
        String email = request.getParameter("email");
        //Check data on valid
        if(email.isEmpty()||email == null){
            request.setAttribute("error2","Введите Email");
            return REGISTERED;
        }
        String forward ="";
        return forward;
    }

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
    public String authorization(HttpServletRequest request, HttpServletResponse response) throws CommandException, ServiceException {
        Connection connection = null;
        String forward = "";
        try {
            /**
             * Get connection from connection pool
             */
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            throw new CommandException(e);
        }
        /**
         * install this connection to ThreadLocal and run Transaction
         */
        ThreadLocal<Connection> local = new ThreadLocal();
        local.set(connection);
        Transaction transaction = new TransactionImpl(local);
        try {
            transaction.start();
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            if (login == null || password == null) {
                forward = ERROR;
                return forward;
            }
            String role = "";
            HttpSession session = request.getSession();
            //ToDo
            AuthorisationService as = new AuthorisationService();
            role = as.authorizeUser(login, password);
            if (role == null) {
                return ERROR;
            }
            switch (role) {
                case "ADMIN":
                    forward = ADMIN;
                    session.setAttribute("role", role);
                    session.setAttribute("login", login);
                    break;
                case "USER":
                    forward = USER;
                    session.setAttribute("role", role);
                    session.setAttribute("login", login);
                    break;
                default:
                    forward = ERROR;
            }
            transaction.commit();
            connectionPool.returnConnection(connection);
        } catch (TransactionException e) {
            throw new TransactionException(e);
        } catch (ServiceException e) {
            throw new ServiceException(e);
        } catch (DaoException e) {
            transaction.rollback();
        }
        return forward;
    }
}
