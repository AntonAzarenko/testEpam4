package com.azarenko.servlets;

import com.azarenko.Services.PublicationService;
import com.azarenko.Services.PublicationServiceImpl;
import com.azarenko.model.Publication;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "admin", urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {
    private PublicationService publicationService;
    private static String INSERT_OR_EDIT = "/pages/add_edit_publications.jsp";
    private static String CATALOG_LIST = "/pages/admin_page.jsp";

    public AdminServlet() {
        publicationService = new PublicationServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Publication publication = null;
        String publicationId = null;
        try {
            publication = new Publication();
            publication.setTitle(req.getParameter("title"));
            publication.setDescription(req.getParameter("discription"));
            publication.setPrice(BigDecimal.valueOf(Double.parseDouble(req.getParameter("price"))));
            publicationId = req.getParameter("catalogId");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        if (publicationId == null || publicationId.isEmpty()) {
            publicationService.add(publication);
        } else {
            publication.setId(Integer.parseInt(publicationId));
            publicationService.update(publication);
        }
        req.setAttribute("catalogs", publicationService.getCatalog());
        req.getRequestDispatcher(CATALOG_LIST).forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "catalog";
        }
        String forward = "";

        if (action.equalsIgnoreCase("delete")) {
            forward = CATALOG_LIST;
            int catalogId = Integer.parseInt(req.getParameter("catalogId"));
            publicationService.remove(catalogId);
            req.setAttribute("catalog", publicationService.getCatalog());
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            int catalogId = Integer.parseInt(req.getParameter("catalogId"));
            Publication publication = publicationService.getPublication(catalogId);
            req.setAttribute("publication", publication);
        } else if (action.equalsIgnoreCase("catalog")) {
            forward = CATALOG_LIST;
            req.setAttribute("catalogs",publicationService.getCatalog());
        } else
            forward = INSERT_OR_EDIT;



    RequestDispatcher view = req.getRequestDispatcher(forward);
        view.forward(req,resp);
}
}
