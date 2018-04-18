package com.azarenko.servlets;

import com.azarenko.model.User;
import com.azarenko.services.Md5Hash;
import com.azarenko.services.UserService;
import com.azarenko.services.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "registration", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(RegistrationServlet.class);
    private static final String REGISTRATION = "/pages/user/registration.jsp";
    private static final String USERPROFILE = "/pages/user/my_profile.jsp";

    private Md5Hash md5Hash;
    private UserService userService;

    public RegistrationServlet() {
        md5Hash = new Md5Hash();
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(REGISTRATION).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info(req.getSession().getAttribute("login"));
        User.UserBulder userBulder = new User.UserBulder();
        String name = req.getParameter("name");
        String password = md5Hash.getMd5Hash(req.getParameter("password"));
        String email = req.getParameter("email");
        userBulder.name(name);
        userBulder.password(password);
        userBulder.email(email);
        User user = userBulder.build();
        userService.addUser(user);
        user = userService.getUserByEmail(email);
        HttpSession session = req.getSession();
        session.setAttribute("login",email);
        req.setAttribute("user",user);
        req.getRequestDispatcher(USERPROFILE).forward(req,resp);

    }
}
