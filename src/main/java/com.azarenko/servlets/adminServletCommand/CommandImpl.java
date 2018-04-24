package com.azarenko.servlets.adminServletCommand;

import com.azarenko.services.Md5Hash;
import com.azarenko.services.ServiceException;
import com.azarenko.services.TransactionException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


public class CommandImpl implements Command {
    private static Map<String, Command> map = new HashMap<>();
    private final Logger log = Logger.getLogger(CommandImpl.class);

    static {
        map.put("catalog", new ShowCatalog(new ArrayOperation()));
        map.put("edit", new Edit(new ArrayOperation()));
        map.put("paymant", new ShowPayment() );
        map.put("delete", new Delete());
        map.put("subscription", new ShowSubscription());
        map.put("authorize", new Authorization(new ArrayOperation()));
        map.put("insert", new Insert(new ArrayOperation()));
        map.put("add", new Add(new ArrayOperation()));
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException {
        String command = request.getParameter("action");
        if(map.containsKey(command)){
            ManagerCommand managerCommand = new ManagerCommand(map.get(command));
            String f = null;
            try {
                f = managerCommand.execute(request,resp);
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
        }else
            return request.getRequestURI();
    }
}
