package com.azarenko.servlets.servletcommands;

import com.azarenko.exceptions.ServiceException;
import com.azarenko.servlets.servletcommands.registercommands.Authorization;
import com.azarenko.servlets.servletcommands.registercommands.Identification;
import com.azarenko.servlets.servletcommands.registercommands.RegistrationRedirect;
import com.azarenko.servlets.servletcommands.registercommands.Registration;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

public class RegistrationCommand implements Command {
    private final static Logger log = Logger.getLogger(RegistrationCommand.class);
    private static ConcurrentHashMap<String, Command> map = new ConcurrentHashMap<>();
    static {
        map.put("authorize", new Authorization());
        map.put("register", new RegistrationRedirect());
        map.put("reg", new Registration());
        map.put("sessionon", new Identification());
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException, UnsupportedEncodingException, SQLException {
        String action = request.getParameter("action");
        log.info(action);
        if(map.containsKey(action)){
            ManagerCommand managerCommand = new ManagerCommand(map.get(action));
            return managerCommand.execute(request, resp);
        }
        return null;
    }
}
