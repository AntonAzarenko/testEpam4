package com.azarenko.servlets.servletCommands;

import com.azarenko.services.ServiceException;
import com.azarenko.util.TransactionException;
import com.azarenko.servlets.servletCommands.adminCommand.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ConcurrentHashMap;


public class CommandAdmin implements Command {
    private static ConcurrentHashMap<String, Command> map = new ConcurrentHashMap<>();

    static {
        map.put("catalog", new ShowCatalogPeriodicals());
        map.put("edit", new EditPeriodical());
        map.put("payment", new ShowPayment());
        map.put("delete", new DeletePeriodical(new ArrayOperationPeriodical()));
        map.put("subscription", new ShowSubscription(new ArrayOperationSubscriptions()));
        map.put("insert", new InsertPeriodical());
        map.put("add", new AddPeriodical());
    }

    private final Logger log = Logger.getLogger(CommandAdmin.class);

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
            }
            return f;
        } else
            return request.getRequestURI();
    }
}
