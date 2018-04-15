package com.azarenko.servlets;

import com.azarenko.dao.PeriodicalsDao;
import com.azarenko.dao.PeriodicalsDaoImpl;
import com.azarenko.dao.UserDao;
import com.azarenko.dao.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MyServlet", urlPatterns = "/user")
public class UsersServlet extends HttpServlet {
    private PeriodicalsDao periodicalsDao;
    private UserDao userDao;

    public UsersServlet() {
        periodicalsDao = new PeriodicalsDaoImpl();
        userDao = new UserDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("catalogs", periodicalsDao.getCatalog());
        req.getRequestDispatcher("/pages/user_catalog.jsp").forward(req, resp);
    }
}
