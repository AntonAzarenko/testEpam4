package com.azarenko.servlets.servletcommands;

import com.azarenko.services.ServiceException;
import com.azarenko.util.TransactionException;
import com.azarenko.servlets.servletcommands.admincommands.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;


public class AdminCommand implements Command {
    private static ConcurrentHashMap<String, Command> map = new ConcurrentHashMap<>();

    static {
        map.put("catalog", new ShowCatalogPeriodicals());
        map.put("edit", new EditPeriodical());
        map.put("payment", new ShowPayment());
        map.put("delete", new DeletePeriodical());
        map.put("subscription", new ShowSubscription());
        map.put("insert", new InsertPeriodical());
        map.put("add", new AddPeriodical());
        map.put("page", new ShowCatalogPeriodicals());
        map.put("exit", new Exit());
        map.put("users", new ShowUsers());
        map.put("start", new Start());
    }

    private final Logger log = Logger.getLogger(AdminCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException {
        String command = request.getParameter("action");
        if (map.containsKey(command)) {
            ManagerCommand managerCommand = new ManagerCommand(map.get(command));
            String f = null;
            try {
                f = managerCommand.execute(request, resp);
            } catch (CommandException e) {
                throw new CommandException(e);
            } catch (TransactionException e) {
                throw new TransactionException(e);
            } catch (ServiceException e) {
                throw new ServiceException(e);
            } catch (UnsupportedEncodingException e) {
                log.error(e);
            } catch (SQLException e) {
                log.error(e);
            }
            return f;
        } else
            return null;
    }
}
