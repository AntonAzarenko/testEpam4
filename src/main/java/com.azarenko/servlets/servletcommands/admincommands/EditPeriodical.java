package com.azarenko.servlets.servletcommands.admincommands;

import com.azarenko.dao.DaoException;
import com.azarenko.model.Periodicals;
import com.azarenko.services.PeriodicalService;
import com.azarenko.services.ServiceException;
import com.azarenko.servlets.servletcommands.CommandException;
import com.azarenko.util.ComponentRegister;
import com.azarenko.util.JdbcTransactionImpl;
import com.azarenko.util.Transaction;
import com.azarenko.util.TransactionException;
import com.azarenko.servlets.servletcommands.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class EditPeriodical implements Command {
    private final String INSERT_OR_EDIT = "/pages/admin/admin_add_edit_publications.jsp";
    private final Logger log = Logger.getLogger(EditPeriodical.class);

    @Override
    /**
     * This method  takes periodical from date base with help some services and redirects user to page for editing.
     */
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException {
        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("catalogId"));
        } catch (NumberFormatException e) {
            log.error(e);
        }
        ComponentRegister register = new ComponentRegister();
        Transaction transaction = (Transaction) register.getImpl(JdbcTransactionImpl.class);
        try {
            transaction.start();
            PeriodicalService service = (PeriodicalService) register.getImpl(PeriodicalService.class);
            Periodicals periodical = service.getPeriodical(id);
            transaction.commit();
            request.setAttribute("periodicals", periodical);
        } catch (TransactionException e) {
            throw new TransactionException(e);
        } catch (DaoException e) {
            log.error("transaction error");
            request.setAttribute("error", "Транзакция закончилась неудачей.");
            transaction.rollback();
        } catch (ServiceException e) {
            throw new ServiceException(e);
        } finally {
            try {
                transaction.reliaseResources();
            } catch (SQLException e) {
                log.error(e);
            }
        }
        return INSERT_OR_EDIT;
    }

}
