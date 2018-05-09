package com.azarenko.servlets.servletcommands.registercommands;

import com.azarenko.dao.DaoException;
import com.azarenko.model.User;
import com.azarenko.services.Md5Hash;
import com.azarenko.services.ServiceException;
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
import java.io.UnsupportedEncodingException;
import java.sql.Connection;

public class Registration implements Command {
    private final String REGISTERED = "/pages/user/registration.jsp";
    private final String USERPROFILE = "/pages/user/my_profile.jsp";

    private final Logger log = Logger.getLogger(Registration.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException, UnsupportedEncodingException {
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
        if (passwordOne.length() < 8) {
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

        //Create new USER.
        User.UserBulder userBulder = new User.UserBulder();
        userBulder.name(name);
        userBulder.email(email);
        userBulder.password(password);
        User user = userBulder.build();
        Connection connection = null;

        ComponentRegister register = new ComponentRegister();
        Transaction transaction = (Transaction) register.getImpl(JdbcTransactionImpl.class);
        try {
            transaction.start();
            UserService service = (UserService) register.getImpl(UserService.class);
            if (service.isUser(email)) {
                request.setAttribute("error", "Такой пользователь существует");
                transaction.rollback();
                return REGISTERED;
            }
            service.addUser(user);
            transaction.commit();

        } catch (TransactionException e) {
            throw new TransactionException(e);
        } catch (DaoException e) {
            transaction.rollback();
            request.setAttribute("error","Транзакция не завершилась успехом");
        }
        String forward = USERPROFILE;
        return forward;
    }
}
