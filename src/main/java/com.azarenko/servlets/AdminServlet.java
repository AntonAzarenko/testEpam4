package com.azarenko.servlets;

import com.azarenko.services.PeriodicalsService;
import com.azarenko.services.PeriodicalsServiceImpl;
import com.azarenko.model.Periodicals;
import org.apache.log4j.Logger;

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

    private final static Logger log = Logger.getLogger(AdminServlet.class);

    private static String INSERT_OR_EDIT = "/pages/admin_add_edit_publications.jsp";
    private static String CATALOG_LIST = "/pages/admin_catalog_page.jsp";

    private PeriodicalsService periodicalsService;

    public AdminServlet() {
        periodicalsService = new PeriodicalsServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        String forward = "";
        if (action.equalsIgnoreCase("delete")) {
            log.debug("redirect to delete");
            forward = CATALOG_LIST;
            int catalogId = Integer.parseInt(req.getParameter("catalogId"));
            periodicalsService.remove(catalogId);
            req.setAttribute("catalog", periodicalsService.getCatalog());
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            log.debug("redirect to edit");
            int catalogId = Integer.parseInt(req.getParameter("catalogId"));
            Periodicals periodicals = periodicalsService.getPublication(catalogId);
            req.setAttribute("periodicals", periodicals);
        } else if (action.equalsIgnoreCase("catalog")) {
            log.info("rediret to catalog");
            forward = CATALOG_LIST;
            req.setAttribute("catalogs", periodicalsService.getCatalog());
        } else if (action.equalsIgnoreCase("insert")) {
            log.debug("redirect to insert");
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = req.getRequestDispatcher(forward);
        view.forward(req, resp);
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
            log.error("NumberFormatException");
        } catch (NullPointerException e) {
            log.error("getParameter null");
        }

        if (publicationId == null || publicationId.isEmpty()) {
            log.debug(" redirect add new periodical");
            periodicalsService.add(periodicals);
        } else {
            log.debug("redirect edit periodical");
            periodicals.setId(Integer.parseInt(publicationId));
            periodicalsService.update(periodicals);
        }

        req.setAttribute("catalogs", periodicalsService.getCatalog());
        req.getRequestDispatcher(CATALOG_LIST).forward(req, resp);
    }
}
