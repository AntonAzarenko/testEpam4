package com.azarenko.servlets.servletCommands.adminCommand;

import com.azarenko.dao.DaoException;
import com.azarenko.model.Periodicals;
import com.azarenko.services.PeriodicalService;
import com.azarenko.services.ServiceException;
import com.azarenko.servlets.servletCommands.Command;
import com.azarenko.servlets.servletCommands.CommandException;
import com.azarenko.util.ComponentRegister;
import com.azarenko.util.JdbcTransactionImpl;
import com.azarenko.util.Transaction;
import com.azarenko.util.TransactionException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.SQLException;

public class AddPeriodical implements Command {
    private final String CATALOG_LIST = "/pages/admin/admin_catalog_page.jsp";
    private final String INSERT_OR_EDIT = "/pages/admin/admin_add_edit_publications.jsp";

    private final Logger log = Logger.getLogger(AddPeriodical.class);

    @Override
    /**
     * This method forwards to the page for Editing and Add getPeriodical. After that it checks data on valid.
     * If parameter  Id equals null, then method adds getPeriodical to database with help of some services. If parameter Id
     * has value, then method redirects to update method.
     *
     * @param request
     * @param response
     * @return
     * @throws CommandException
     * @throws ServiceException
     * @throws UnsupportedEncodingException
     * @Author Azarenko Anton
     */
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException, UnsupportedEncodingException {
        /**
         * Create reference to object
         */
        log.info("Start adding");
        Periodicals periodical = new Periodicals();

        /**
         * get data from request and check to valid
         */
        String title = request.getParameter("title");
        if (title == null || title.equals("")) {
            log.info(title);
            request.setAttribute("error1", "Введите названиe...");
            return INSERT_OR_EDIT;
        }
        periodical.setTitle(title);
        String output_frequncy = request.getParameter("outfrequency");
        int outputFrequency = 0;
        if (output_frequncy == null || output_frequncy.equals("")) {
            request.setAttribute("error2", "Введите данные");
            request.setAttribute("periodicals", periodical);
            return INSERT_OR_EDIT;
        } else {
            try {
                outputFrequency = Integer.parseInt(output_frequncy);
            } catch (NumberFormatException e) {
                request.setAttribute("error3", "Неверный формат данных");
                request.setAttribute("periodicals", periodical);
                return INSERT_OR_EDIT;
            }
            if (outputFrequency < 0 || outputFrequency > 30) {
                request.setAttribute("error3", "Неверный формат данных");
                request.setAttribute("periodicals", periodical);
                return INSERT_OR_EDIT;
            }
        }
        periodical.setOutputFrequency(outputFrequency);
        String discription = request.getParameter("discription");
        if (discription == null || discription.equals("")) {

            request.setAttribute("error4", "Напишите описание");
            request.setAttribute("periodicals", periodical);
            return INSERT_OR_EDIT;
        }
        periodical.setDescription(discription);
        String priceS = request.getParameter("price");
        BigDecimal price = null;
        if (priceS == null || priceS.equals("")) {
            request.setAttribute("error5", "Введите цену.");
            request.setAttribute("periodicals", periodical);
            return INSERT_OR_EDIT;
        } else if (priceS.equals("0")) {
            request.setAttribute("error5", "Введите цену.");
            request.setAttribute("periodicals", periodical);
            return INSERT_OR_EDIT;
        } else {
            try {
                price = BigDecimal.valueOf(Double.parseDouble(priceS));
            } catch (NumberFormatException e) {
                request.setAttribute("error6", "Неверный формат данных");
                request.setAttribute("periodicals", periodical);
                return INSERT_OR_EDIT;
            }
        }
        String id = request.getParameter("catalogId");
        if (id == null || id.isEmpty()) {
        } else {
            //redirect to method "Update"
            int cid = 0;
            try {
                cid = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                throw new CommandException(e);
            }
            periodical.setId(cid);
            periodical.setTitle(title);
            periodical.setDescription(discription);
            periodical.setPrice(price);
            request.setAttribute("periodicals", periodical);
            update(request, resp);
            return CATALOG_LIST;
        }

        /**
         *initialize and fill object
         **/
        periodical.setTitle(title);
        periodical.setDescription(discription);
        periodical.setPrice(price);

        ComponentRegister register = new ComponentRegister();
        Transaction transaction = (Transaction) register.getImpl(JdbcTransactionImpl.class);
        try {
            log.info("transaction start");
            transaction.start();
            PeriodicalService service = (PeriodicalService) register.getImpl(PeriodicalService.class);
            service.add(periodical);
            request.setAttribute("catalogs", service.getCatalog());
            transaction.commit();
        } catch (TransactionException e) {
            throw new TransactionException(e);
        } catch (DaoException e) {
            transaction.rollback();
            request.setAttribute("error", "транзакция закончилась неудачей");
            log.error(e);
        } catch (ServiceException e) {
            new ServiceException(e);
        } finally {
            try {
                transaction.reliaseResources();
            } catch (SQLException e) {
                log.error(e);
            }
        }
        return CATALOG_LIST;
    }

    /**
     *
     * @param request
     * @param response
     * @return
     * @throws CommandException
     * @throws ServiceException
     */
    public String update(HttpServletRequest request, HttpServletResponse response) throws CommandException, ServiceException {
        ComponentRegister register = new ComponentRegister();
        Transaction transaction = (Transaction) register.getImpl(JdbcTransactionImpl.class);
        try {
            transaction.start();
            PeriodicalService service = (PeriodicalService) register.getImpl(PeriodicalService.class);
            service.update((Periodicals) request.getAttribute("periodicals"));
            request.setAttribute("catalogs", service.getCatalog());
            transaction.commit();
        } catch (TransactionException e) {
            throw new TransactionException(e);
        } catch (DaoException e) {
            transaction.rollback();
            request.setAttribute("error", "транзакция закончилась неудачей");
            log.error(e);
        } catch (ServiceException e) {
            throw new ServiceException(e);
        } finally {
            try {
                transaction.reliaseResources();
            } catch (SQLException e) {
                log.error(e);
            }
        }
        return CATALOG_LIST;
    }
}
