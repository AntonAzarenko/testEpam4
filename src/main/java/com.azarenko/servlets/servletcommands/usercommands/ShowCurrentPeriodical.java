package com.azarenko.servlets.servletcommands.usercommands;

import com.azarenko.dao.DaoException;
import com.azarenko.model.Periodicals;
import com.azarenko.services.PeriodicalService;
import com.azarenko.services.ServiceException;
import com.azarenko.servlets.servletcommands.Command;
import com.azarenko.servlets.servletcommands.CommandException;
import com.azarenko.servlets.servletcommands.admincommands.ShowCatalogPeriodicals;
import com.azarenko.util.ComponentRegister;
import com.azarenko.util.JdbcTransactionImpl;
import com.azarenko.util.Transaction;
import com.azarenko.util.TransactionException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public class ShowCurrentPeriodical implements Command {
    private final String USER_CATALOG_CURRENT = "/pages/user/user_catalog_current.jsp";
    private final Logger log = Logger.getLogger(ShowCurrentPeriodical.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException, UnsupportedEncodingException {
        ShowCatalogPeriodicals periodicals = new ShowCatalogPeriodicals();
        periodicals.execute(request, resp);
        ComponentRegister register = new ComponentRegister();
        Transaction transaction = (Transaction) register.getImpl(JdbcTransactionImpl.class);
        try {
            transaction.start();
            PeriodicalService service = (PeriodicalService) register.getImpl(PeriodicalService.class);
            Periodicals periodical = service.getPeriodical(Integer.parseInt(request.getParameter("periodicalId")));
            request.setAttribute("periodical", periodical);
            transaction.commit();
        } catch (TransactionException e) {
            throw new TransactionException(e);
        } catch (DaoException e) {
            transaction.rollback();
            log.error(e);
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
        return USER_CATALOG_CURRENT;
    }
}
