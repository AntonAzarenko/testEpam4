package com.azarenko.servlets;

import com.azarenko.dao.CatalogDao;
import com.azarenko.dao.CatalogDaoImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet(name = "MyServlet", urlPatterns = "/pages")
public class UsersServlet extends HttpServlet {
    private CatalogDao catalogDao;

    public UsersServlet() {
        catalogDao = new CatalogDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("catalogs", catalogDao.getCatalog());
        req.getRequestDispatcher("/pages/catalog.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("catalogs", catalogDao.getCatalog());
        req.getRequestDispatcher("/pages/catalog.jsp").forward(req, resp);
    }
}
