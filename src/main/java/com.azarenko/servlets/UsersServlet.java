package com.azarenko.servlets;

import com.azarenko.Services.PublicationService;
import com.azarenko.Services.PublicationServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MyServlet", urlPatterns = "/user")
public class UsersServlet extends HttpServlet {
    private PublicationService publicationService;

    public UsersServlet() {
        publicationService = new PublicationServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("catalogs", publicationService.getCatalog());
        req.getRequestDispatcher("/pages/user_catalog.jsp").forward(req, resp);
    }
}
