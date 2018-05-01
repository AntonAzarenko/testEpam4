package com.azarenko.servlets.servletCommands.registerOperation;

import com.azarenko.dao.DaoException;
import com.azarenko.model.User;
import com.azarenko.services.*;
import com.azarenko.servlets.servletCommands.CommandException;
import com.azarenko.servlets.servletCommands.adminOperations.ArrayOperationPeriodical;
import com.azarenko.util.ComponentRegister;
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
    private final String USER = "user?action=start";
    private final String START = "/pages/authorize.jsp";
    private final String USERPROFILE = "/pages/user/my_profile.jsp";

    private final Logger log = Logger.getLogger(ArrayOperationPeriodical.class);

    private ConnectionPool connectionPool;

    public ArrayOperationRegistration()  {
        try {
            connectionPool = ConnectionPool.getInstance();
        } catch (ServiceException e) {
            log.error(e);
        }
    }

    /**
     * this method redirects to rhe page "registration"
     *
     * @param request
     * @param resp
     * @return
     */
    public String registrationRedirect(HttpServletRequest request, HttpServletResponse resp) {
        String forward = REGISTERED;
        return forward;
    }

    /**
     * @param request
     * @param response
     * @return
     */
    public String registration(HttpServletRequest request, HttpServletResponse response) throws CommandException, TransactionException {
        String name = request.getParameter("name");
        //Check data on valid
        if (name.isEmpty() || name == null) {
            request.setAttribute("error", "Введите имя");
            return REGISTERED;
        }
        String email = request.getParameter("email");
        //Check data on valid
        if (email.isEmpty() || email == null) {
            request.setAttribute("error", "Введите Email");
            return REGISTERED;
        }
        String password = "";
        String passwordOne = request.getParameter("password");
        //Check data on valid
        if (passwordOne.isEmpty() || passwordOne == null) {
            request.setAttribute("error", "Введите пароль");
            return REGISTERED;
        }
        if(passwordOne.length() < 8){
            request.setAttribute("error", "пароль должен быть не менее 8 символов");
            return REGISTERED;
        }
        String passwordTwo = request.getParameter("password_two");
        //Check data on valid
        if (passwordTwo.isEmpty() || passwordTwo == null) {
            request.setAttribute("error", "Подтвердите пароль");
            return REGISTERED;
        } else if (passwordTwo.equals(passwordOne)) {
            Md5Hash md5Hash = new Md5Hash();
            password = md5Hash.getMd5Hash(passwordOne);
        } else {
            request.setAttribute("error", "Разные пароли...");
            return REGISTERED;
        }
        log.info(passwordOne + passwordTwo);
        //Create new USER.
        User.UserBulder userBulder = new User.UserBulder();
        userBulder.name(name);
        userBulder.email(email);
        userBulder.password(password);
        User user = userBulder.build();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            throw new CommandException(e);
        }
        ThreadLocal<Connection> local = new ThreadLocal<>();
        local.set(connection);
        Transaction transaction = new TransactionImpl(local);
        try {
            transaction.start();
            ComponentRegister register = new ComponentRegister();
            UserService service = (UserService) register.getImpl(UserService.class);
            if(service.isUser(email)){
                log.info("Проверяем на наличие пользователя");
                request.setAttribute("error","Такой пользователь существует");
                transaction.rollback();
                connectionPool.returnConnection(connection);
                return REGISTERED;
            }
            service.addUser(user);
            transaction.commit();
            connectionPool.returnConnection(connection);
        } catch (TransactionException e) {
            throw new TransactionException(e);
        } catch (DaoException e) {
            transaction.rollback();
            log.error(e);
        }
        String forward = USERPROFILE;
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
            if (login == null || login.isEmpty()) {
                request.setAttribute("error", "Введите логин");
                return START;
            } else if (password == null || password.isEmpty()) {
                request.setAttribute("error", "Введите пароль");
                return START;
            }
            String role = "";
            HttpSession session = request.getSession();
            //ToDo
            AuthorisationService as = new AuthorisationService();
            role = as.authorizeUser(login, password);
            if (role == null || role.isEmpty()) {
                request.setAttribute("error", "Неверный логин или пароль");
                return START;
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
                    request.setAttribute("error", "Неверный логин или пароль");
                    forward = START;
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
