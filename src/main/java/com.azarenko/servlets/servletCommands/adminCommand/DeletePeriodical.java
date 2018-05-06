package com.azarenko.servlets.servletCommands.adminCommand;

import com.azarenko.dao.DaoException;
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
import java.sql.SQLException;

public class DeletePeriodical implements Command {
    private final String CATALOG_LIST = "/pages/admin/admin_catalog_page.jsp";
    private final Logger log = Logger.getLogger(ArrayOperationPeriodical.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException {
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
        ComponentRegister register = new ComponentRegister();
        Transaction transaction = (Transaction) register.getImpl(JdbcTransactionImpl.class);
        try {
            transaction.start();
            PeriodicalService service = (PeriodicalService) register.getImpl(PeriodicalService.class);
            service.remove(catalodId);
            request.setAttribute("catalogs", service.getCatalog());
            transaction.commit();
        } catch (TransactionException e) {
            throw new TransactionException(e);
        } catch (DaoException e) {
            transaction.rollback();
            log.error(e);
            request.setAttribute("error","Транзакция закончилась неудачей");
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
}
