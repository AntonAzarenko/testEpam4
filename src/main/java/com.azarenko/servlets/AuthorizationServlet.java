package com.azarenko.servlets;

import com.azarenko.services.AutorisationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "authorize", urlPatterns = "/pages/authorize")
public class AuthorizationServlet extends HttpServlet {

    private AutorisationService authorized;

    public AuthorizationServlet() {
        authorized = new AutorisationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/admin").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("username");
        String password = req.getParameter("password");
        String role = authorized.authorizeUser(login,password);
        if(role == null){
            req.getRequestDispatcher("/pages/erors_message.jsp").forward(req, resp);
        }
            switch (role){
                case  "ADMIN" :
                    doGet(req,resp);
                    break;
                case "USER" :
                    req.getRequestDispatcher("/user").forward(req,resp);
                    break;
                  default:
                      req.getRequestDispatcher("/pages/erors_message.jsp").forward(req,resp);
            }
    }
}
