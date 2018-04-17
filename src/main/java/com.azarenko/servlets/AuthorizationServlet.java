package com.azarenko.servlets;

import com.azarenko.services.AutorisationService;
import com.azarenko.dao.UserDao;
import com.azarenko.dao.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "authorize", urlPatterns = "/authorize")
public class AuthorizationServlet extends HttpServlet {

    private AutorisationService authorized;
    private UserDao userDao;

    public AuthorizationServlet() {
        authorized = new AutorisationService();
        userDao = new UserDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/start.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        HttpSession session = req.getSession();
        String password = req.getParameter("password");
        String role = authorized.authorizeUser(login,password);
        if(role == null){
            req.getRequestDispatcher("/pages/erors_message.jsp").forward(req, resp);
        }
            switch (role){
                case  "ADMIN" :
                    session.setAttribute("login", login);
                    req.getRequestDispatcher("pages/admin_start_page.jsp").forward(req,resp);
                    break;
                case "USER" :
                    session.setAttribute("login", login);
                    req.getRequestDispatcher("/user?action=catalog").forward(req,resp);
                    break;
                  default:
                      req.getRequestDispatcher("/pages/erors_message.jsp").forward(req,resp);
            }
    }
}
