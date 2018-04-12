package com.azarenko.servlets;

import com.azarenko.services.PeriodicalsService;
import com.azarenko.services.PeriodicalsServiceImpl;
import com.azarenko.services.UserService;
import com.azarenko.services.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MyServlet", urlPatterns = "/user")
public class UsersServlet extends HttpServlet {
    private PeriodicalsService periodicalsService;
    private UserService userService;

    public UsersServlet() {
        periodicalsService = new PeriodicalsServiceImpl();
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("user");
        
        req.setAttribute("catalogs", periodicalsService.getCatalog());
        req.getRequestDispatcher("/pages/user_catalog.jsp").forward(req, resp);
    }
}
