package com.azarenko.servlets.adminServletCommand;

import com.azarenko.dao.DaoException;
import com.azarenko.model.Periodicals;
import com.azarenko.services.*;
import com.azarenko.util.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 */
public class ArrayOperation {

    private final String INSERT_OR_EDIT = "/pages/admin/admin_add_edit_publications.jsp";
    private final String CATALOG_LIST = "/pages/admin/admin_catalog_page.jsp";
    private final String PAYMENT = "/pages/admin/payment.jsp";
    private final String SUBSCRIPTION = "/pages/admin/subscription.jsp";
    private final String ERROR = "/pages/info/erors_message.jsp";
    private final String ADMIN = "pages/admin/admin_start_page.jsp";
    private final String USER = "user?action=catalog";
    private final String START = "/pages/start.jsp";
    private final String REGISTERED = "/pages/user/registration.jsp";

    private ConnectionPool connectionPool;

    private final Logger log = Logger.getLogger(ArrayOperation.class);


    public ArrayOperation() {
        connectionPool = ConnectionPool.getInstance();
    }

    /**
     * This method  implements  access to database "catalog periodicals" with help of some services and it gets ArrayList
     * with all objects. This method installs this ArrayList in attribute parameter req. After that it returns url
     * for forwarding to the page.
     *
     * @param req  - this parameter forwarding from servlet
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
            PeriodicalService service = new PeriodicalServiceImpl();
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
     * This method gets login and password from parameter "request" and forwards them to checking. If user  is in a
     * database and password is confirmed, then this method gets parameter "role" and returns url depending of
     * the parameter "role"
     *
     * @param request
     * @param response
     * @return
     * @throws CommandException
     * @throws ServiceException
     * @Author Azarenko Anton
     */
    public String authorization(HttpServletRequest request, HttpServletResponse response) throws CommandException, ServiceException {
        Connection connection = null;
        String forward = "";
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
        ThreadLocal<Connection> local = new ThreadLocal();
        local.set(connection);
        Transaction transaction = new TransactionImpl(local);
        try {
            transaction.start();
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            if (login == null || password == null) {
                forward = ERROR;
                return forward;
            }
            String role = "";
            HttpSession session = request.getSession();
            AuthorisationService as = new AuthorisationService();
            role = as.authorizeUser(login, password);
            if (role == null) {
                return ERROR;
            }
            switch (role) {
                case "ADMIN":
                    forward = ADMIN;
                    session.setAttribute("role", role);
                    session.setAttribute("login", login);
                    break;
                case "USER":
                    forward = USER;
                    session.setAttribute("role", role);
                    session.setAttribute("login", login);
                    break;
                default:
                    forward = ERROR;
            }
            transaction.commit();
            connectionPool.returnConnection(connection);
        } catch (TransactionException e) {
            throw new TransactionException(e);
        } catch (ServiceException e) {
            throw new ServiceException(e);
        } catch (DaoException e) {
            transaction.rollback();
        }
        return forward;
    }

    /**
     * This method forvards on pager for edit or Add
     *
     * @param request
     * @param response
     * @return url
     */
    public String insert(HttpServletRequest request, HttpServletResponse response) {
        return INSERT_OR_EDIT;
    }

    public String add(HttpServletRequest request, HttpServletResponse response) throws CommandException, TransactionException, UnsupportedEncodingException {
        /**
         * Create reference to object
         */
        log.info("Start adding");
        Periodicals periodical = new Periodicals();

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");



        log.info(request.getCharacterEncoding() + " "+ response.getCharacterEncoding());


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
          //  request.setAttribute("periodicals",periodical);
            return INSERT_OR_EDIT;
        } else  {
            try {
                outputFrequency = Integer.parseInt(output_frequncy);
            } catch (NumberFormatException e) {
                request.setAttribute("error3", "Неверный формат данных");
              //  request.setAttribute("periodicals",periodical);
                return INSERT_OR_EDIT;
            }
            if(outputFrequency < 0 || outputFrequency > 30){
                request.setAttribute("error3", "Неверный формат данных");
              //  request.setAttribute("periodicals",periodical);
                return INSERT_OR_EDIT;
            }
        }
        periodical.setOutputFrequency(outputFrequency);
        String discription = request.getParameter("discription");
        if (discription == null || discription.equals("")) {

            request.setAttribute("error4", "Напишите описание");
          //  request.setAttribute("periodicals",periodical);
            return INSERT_OR_EDIT;
        }
        periodical.setDescription(discription);
        String priceS = request.getParameter("price");
        BigDecimal price = null;
        if (priceS == null||priceS.equals("")) {
            request.setAttribute("error5", "Введите цену.");
           // request.setAttribute("periodicals",periodical);
            return INSERT_OR_EDIT;
        } else if(priceS.equals("0")) {
            request.setAttribute("error5", "Введите цену.");
         //   request.setAttribute("periodicals",periodical);
            return INSERT_OR_EDIT;
        } else {
            try {
                price = BigDecimal.valueOf(Double.parseDouble(priceS));
            } catch (NumberFormatException e) {
                request.setAttribute("error6", "Неверный формат данных");
             //   request.setAttribute("periodicals",periodical);
                return INSERT_OR_EDIT;
            }
        }
        if(request.getParameter("id") == null || (request.getParameter("id").equals(""))) {
            int id;
        }else {
            int id = Integer.parseInt(request.getParameter("id"));
            periodical.setId(id);
        }

        /**
          *initialize and fill object
         **/
        periodical.setTitle(title);
        periodical.setDescription(discription);
        periodical.setPrice(price);
        PeriodicalService service = new PeriodicalServiceImpl();
        /**
         * get connection from connection pool and start transaction
         */
        Connection connection = null;
        try {
             connection = connectionPool.getConnection();
        } catch (SQLException e) {
            throw new CommandException(e);
        }
        ThreadLocal<Connection> local= new ThreadLocal<>();
        local.set(connection);
        Transaction transaction = new TransactionImpl(local);
        try {
            log.info("transaction start");
            transaction.start();
            service.add(periodical);
            request.setAttribute("catalogs",service.getCatalog());
            transaction.commit();
            connectionPool.returnConnection(connection);
        } catch (TransactionException e) {
            throw new TransactionException(e);
        } catch (DaoException e) {
            transaction.rollback();
        } catch (ServiceException e) {
            new ServiceException(e);
        }
        return CATALOG_LIST;
    }

    public String edit(HttpServletRequest request, HttpServletResponse resp) {
        return INSERT_OR_EDIT;
    }
}
