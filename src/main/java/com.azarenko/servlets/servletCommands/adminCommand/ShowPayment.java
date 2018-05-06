package com.azarenko.servlets.servletCommands.adminCommand;

import com.azarenko.dao.DaoException;
import com.azarenko.services.PaymentService;
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

public class ShowPayment implements Command {
    private final String PAYMENT = "/pages/admin/payment.jsp";

    private final Logger log = Logger.getLogger(ShowPayment.class);

    @Override
    /**
     * This method implements access to data base with help to some method. Then  get Arraylist with objects and install
     * them to parameter "request".
     * @param request
     * @param response
     * @return
     * @throws CommandException
     * @throws ServiceException
     */
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException, SQLException {
        Transaction transaction = new JdbcTransactionImpl();
        try {
            transaction.start();
            ComponentRegister register = new ComponentRegister();
            PaymentService service = (PaymentService) register.getImpl(PaymentService.class);
            request.setAttribute("paymentList", service.getPaymentList());
            transaction.commit();
        } catch (TransactionException e) {
            log.error(e);
            throw new TransactionException(e);
        } catch (DaoException e) {
            transaction.rollback();
            log.error(e);
            throw new TransactionException(e);
        } catch (ServiceException e) {
            log.error(e);
            throw new ServiceException(e);
        }
        finally {
            transaction.reliaseResources();
        }
        return PAYMENT;
    }
}
