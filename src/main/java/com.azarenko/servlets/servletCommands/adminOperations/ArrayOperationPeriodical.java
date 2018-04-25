package com.azarenko.servlets.servletCommands.adminOperations;

import com.azarenko.dao.DaoException;
import com.azarenko.model.Periodicals;
import com.azarenko.services.*;
import com.azarenko.servlets.servletCommands.CommandException;
import com.azarenko.util.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 */
public class ArrayOperationPeriodical {

    private final String INSERT_OR_EDIT = "/pages/admin/admin_add_edit_publications.jsp";
    private final String CATALOG_LIST = "/pages/admin/admin_catalog_page.jsp";

    private final Logger log = Logger.getLogger(ArrayOperationPeriodical.class);

    private ConnectionPool connectionPool;


    public ArrayOperationPeriodical() {
        connectionPool = ConnectionPool.getInstance();
    }

    /**
     * This method  implements  access to database "catalog periodicals" with help of some services and it gets ArrayList
     * with all objects. This method installs this ArrayList in attribute parameter req. After that it returns url
     * for forwarding to the page.
     *
     * @param req  this parameter forwarding from servlet
     * @param resp this parameter forwarding from servlet
     * @return URL
     * @throws CommandException
     * @throws ServiceException
     * @Author AzarenkoAnton
     */
    public String showCatalog(HttpServletRequest req, HttpServletResponse resp) throws CommandException, ServiceException {
        Connection connection = null;
        try {
            /**
             * Get connection from connection pool
             */
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            throw new CommandException(e);
        }
        /**
         * install this connection to ThreadLocal and run Transaction
         */
        ThreadLocal local = new ThreadLocal();
        local.set(connection);
        Transaction transaction = new TransactionImpl(local);
        try {
            transaction.start();

            ComponentRegister register = new ComponentRegister();
            PeriodicalService service = (PeriodicalService) register.getImpl(PeriodicalService.class);
            req.setAttribute("catalogs", service.getCatalog());
            /**
             * close transaction
             */
            transaction.commit();
            /**
             * return connection to connection pool
             */
            connectionPool.returnConnection(connection);
        } catch (TransactionException e) {
            throw new TransactionException(e);
        } catch (DaoException e) {
            transaction.rollback();
        } catch (ServiceException e) {
            throw new ServiceException(e);
        }
        return CATALOG_LIST;
    }

    /**
     * This method forvards on pager for edit or AddPeriodical
     *
     * @param request
     * @param response
     * @return url
     */
    public String insert(HttpServletRequest request, HttpServletResponse response) {
        return INSERT_OR_EDIT;
    }

    /**
     * This method forwards to the page for Editing and Add periodical. After that it checks data on valid.
     * If parameter  Id equals null, then method adds periodical to database with help of some services. If parameter Id
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
    public String add(HttpServletRequest request, HttpServletResponse response) throws CommandException, ServiceException, UnsupportedEncodingException {
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
            update(request, response);
            return CATALOG_LIST;
        }

        /**
         *initialize and fill object
         **/
        periodical.setTitle(title);
        periodical.setDescription(discription);
        periodical.setPrice(price);
                //ToDO
        PeriodicalService service = new PeriodicalServiceImplImpl();
        /**
         * get connection from connection pool and start transaction
         */
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            throw new CommandException(e);
        }
        ThreadLocal<Connection> local = new ThreadLocal<>();
        local.set(connection);
        Transaction transaction = new TransactionImpl(local);
        try {
            log.info("transaction start");
            transaction.start();
            service.add(periodical);
            request.setAttribute("catalogs", service.getCatalog());
            transaction.commit();
            connectionPool.returnConnection(connection);
        } catch (TransactionException e) {
            throw new TransactionException(e);
        } catch (DaoException e) {
            transaction.rollback();
            log.error(e);
        } catch (ServiceException e) {
            new ServiceException(e);
        }
        return CATALOG_LIST;
    }

    /**
     *
     * @param request
     * @param resp
     * @return
     * @throws CommandException
     * @throws TransactionException
     */
    public String edit(HttpServletRequest request, HttpServletResponse resp) throws CommandException, TransactionException {
        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("catalogId"));
        } catch (NumberFormatException e) {
            log.error(e);
        }
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            throw new CommandException(e);
        }
        ThreadLocal<Connection> local = new ThreadLocal<>();
        local.set(connection);
        Transaction transaction = new TransactionImpl(local);
        try {
            transaction.start();
            //ToDo
            PeriodicalService service = new PeriodicalServiceImplImpl();
            Periodicals periodical = service.getPeriodical(id);
            transaction.commit();
            connectionPool.returnConnection(connection);
            request.setAttribute("periodicals", periodical);
        } catch (TransactionException e) {
            throw new TransactionException(e);
        } catch (DaoException e) {
            log.error("transaction error");
            transaction.rollback();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return INSERT_OR_EDIT;
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
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            throw new CommandException(e);
        }
        ThreadLocal<Connection> local = new ThreadLocal<>();
        local.set(connection);
        Transaction transaction = new TransactionImpl(local);
        try {
            transaction.start();
            //ToDo
            PeriodicalService service = new PeriodicalServiceImplImpl();
            service.update((Periodicals) request.getAttribute("periodicals"));
            request.setAttribute("catalogs", service.getCatalog());
            transaction.commit();
            connectionPool.returnConnection(connection);
        } catch (TransactionException e) {
            throw new TransactionException(e);
        } catch (DaoException e) {
            transaction.rollback();
        } catch (ServiceException e) {
            throw new ServiceException(e);
        }

        return CATALOG_LIST;
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws CommandException
     * @throws ServiceException
     */
    public String delete(HttpServletRequest request, HttpServletResponse response) throws CommandException, ServiceException {
        String id = request.getParameter("catalogId");
        int catalodId = 0;
        if (id == null || id.isEmpty()) {
            return CATALOG_LIST;
        } else {
            try {
                catalodId = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                log.error("неправильный формат числа");
            }
        }
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            throw new CommandException(e);
        }
        ThreadLocal<Connection> local = new ThreadLocal<>();
        local.set(connection);
        Transaction transaction = new TransactionImpl(local);
        try {
            transaction.start();
            //ToDO
            PeriodicalService service = new PeriodicalServiceImplImpl();
            service.remove(catalodId);
            request.setAttribute("catalogs", service.getCatalog());
            transaction.commit();
            connectionPool.returnConnection(connection);
        } catch (TransactionException e) {
            throw new TransactionException(e);
        } catch (DaoException e) {
            transaction.rollback();
        } catch (ServiceException e) {
            throw new ServiceException(e);
        }
        return CATALOG_LIST;
    }
}
