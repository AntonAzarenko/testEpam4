package com.azarenko.servlets;

import com.azarenko.util.Authorized;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "authorize", urlPatterns = "/pages/authorize")
public class AuthorisedServlet extends HttpServlet {

    private Authorized authorized;

    public AuthorisedServlet() {
        authorized = new Authorized();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("username");
        String password = req.getParameter("password");
        String role = authorized.authorizeUser(login,password);
            switch (role){
                case  "ADMIN" :
                    req.getRequestDispatcher("/admin").forward(req,resp);
                    break;
                case "USER" :
                    req.getRequestDispatcher("/user").forward(req,resp);
                    break;
                  default:
                      req.getRequestDispatcher("/pages/erorsMessage.jsp").forward(req,resp);
            }
        req.getRequestDispatcher("/pages/erorsMessage.jsp").forward(req,resp);
    }
}
