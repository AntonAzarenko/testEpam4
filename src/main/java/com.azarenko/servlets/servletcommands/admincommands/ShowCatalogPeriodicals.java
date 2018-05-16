package com.azarenko.servlets.servletcommands.admincommands;

import com.azarenko.dao.DaoException;
import com.azarenko.model.Periodicals;
import com.azarenko.services.*;
import com.azarenko.servlets.servletcommands.Command;
import com.azarenko.servlets.servletcommands.CommandException;
import com.azarenko.util.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class
ShowCatalogPeriodicals implements Command {
    private final String CATALOG_LIST = "/pages/admin/admin_catalog_page.jsp";

    private final Logger log = Logger.getLogger(ShowCatalogPeriodicals.class);

    @Override
    /**
     * This method  implements  access to database "catalog periodicals" with help of some services and it gets ArrayList
     * with all objects. This method installs this ArrayList in attribute parameter req. After that it returns url
     * for forwarding to the page.
     * @param request
     * @param response
     */
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException {
        ComponentRegister register = new ComponentRegister();
        Transaction transaction = (Transaction) register.getImpl(JdbcTransactionImpl.class);
        try {
            transaction.start();
            PeriodicalService service = (PeriodicalService) register.getImpl(PeriodicalService.class);
            List<Periodicals> per = service.getCatalog();
            setPadding(request, per);
            transaction.commit();
        } catch (TransactionException e) {
            throw new TransactionException(e);
        } catch (DaoException e) {
            transaction.rollback();
            log.error(e);
            request.setAttribute("error", "транзакция закончилась неудачей");
        } catch (ServiceException e) {
            throw new ServiceException(e);
        }
        finally {
            try {
                transaction.reliaseResources();
            } catch (SQLException e) {
                log.error(e);
            }
        }
        return CATALOG_LIST;
    }

    /**
     * this method is responsible for nubmer of paginated rows on the user page.
     * in consctructor this method sends arrayList , and sends number of paginated rows.
     * Class PageListHolder returns namber of  pages based on the size of the ArrayList
     * @param req
     * @param list
     */
    public void setPadding(HttpServletRequest req, List<Periodicals> list) {
        PageListHolder<Periodicals> listHolder = new PageListHolder<>(list);
        listHolder.setPageSize(8);
        Integer page = 0;
        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (NumberFormatException e) {

        }
        req.setAttribute("maxPages", listHolder.getPageCount());
        String URI = req.getRequestURI();
        req.setAttribute("currentsort", URI + "?action=page");
        if (page == null || page < 1 || page > listHolder.getPageCount())
            page = 1;

        req.setAttribute("page", page);
        listHolder.setPage(page - 1);
        req.setAttribute("catalogs", listHolder.getPageList());
    }

}
