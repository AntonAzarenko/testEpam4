package com.azarenko.servlets;

import com.azarenko.services.PeriodicalsService;
import com.azarenko.services.PeriodicalsServiceImpl;
import com.azarenko.model.Periodicals;

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
    private PeriodicalsService periodicalsService;
    private static String INSERT_OR_EDIT = "/pages/admin_add_edit_publications.jsp";
    private static String CATALOG_LIST = "/pages/admin_start_page.jsp";

    public AdminServlet() {
        periodicalsService = new PeriodicalsServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) {
            action = "catalog";
        }
        String forward = "";

        if (action.equalsIgnoreCase("delete")) {
            forward = CATALOG_LIST;
            int catalogId = Integer.parseInt(req.getParameter("catalogId"));
            periodicalsService.remove(catalogId);
            req.setAttribute("catalog", periodicalsService.getCatalog());
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            int catalogId = Integer.parseInt(req.getParameter("catalogId"));
            Periodicals periodicals = periodicalsService.getPublication(catalogId);
            req.setAttribute("periodicals", periodicals);
        } else if (action.equalsIgnoreCase("catalog")) {
            forward = CATALOG_LIST;
            req.setAttribute("catalogs", periodicalsService.getCatalog());
        } else
            forward = INSERT_OR_EDIT;



        RequestDispatcher view = req.getRequestDispatcher(forward);
        view.forward(req,resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Periodicals periodicals = null;
        String publicationId = null;
        try {
            periodicals = new Periodicals();
            periodicals.setTitle(req.getParameter("title"));
            periodicals.setDescription(req.getParameter("discription"));
            periodicals.setPrice(BigDecimal.valueOf(Double.parseDouble(req.getParameter("price"))));
            publicationId = req.getParameter("catalogId");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        if (publicationId == null || publicationId.isEmpty()) {
            periodicalsService.add(periodicals);
        } else {
            periodicals.setId(Integer.parseInt(publicationId));
            periodicalsService.update(periodicals);
        }
        req.setAttribute("catalogs", periodicalsService.getCatalog());
        req.getRequestDispatcher(CATALOG_LIST).forward(req, resp);
}
}
