package com.azarenko.servlets.servletcommands.admincommands;

import com.azarenko.dao.DaoException;
import com.azarenko.model.Periodical;
import com.azarenko.services.PeriodicalService;
import com.azarenko.exceptions.ServiceException;
import com.azarenko.servlets.servletcommands.Command;
import com.azarenko.servlets.servletcommands.CommandException;
import com.azarenko.util.ComponentRegister;
import com.azarenko.util.JdbcTransactionImpl;
import com.azarenko.util.Transaction;
import com.azarenko.util.TransactionException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class DeletePeriodical implements Command {
    private final String CATALOG_LIST = "/pages/admin/admin_catalog_page.jsp";
    private final Logger log = Logger.getLogger(DeletePeriodical.class);

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
            List<Periodical> per = service.getCatalog();
            new ShowCatalogPeriodicals().setPadding(request, per);
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
