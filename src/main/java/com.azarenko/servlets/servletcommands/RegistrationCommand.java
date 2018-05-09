package com.azarenko.servlets.servletcommands;

import com.azarenko.services.ServiceException;
import com.azarenko.servlets.servletcommands.registercommands.Authorization;
import com.azarenko.servlets.servletcommands.registercommands.RegistrationRedirect;
import com.azarenko.servlets.servletcommands.registercommands.Registration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

public class RegistrationCommand implements Command {
    private static ConcurrentHashMap<String, Command> map = new ConcurrentHashMap<>();
    static {
        map.put("authorize", new Authorization());
        map.put("register", new RegistrationRedirect());
        map.put("reg", new Registration());
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException, UnsupportedEncodingException, SQLException {
        String action = request.getParameter("action");
        if(map.containsKey(action)){
            ManagerCommand managerCommand = new ManagerCommand(map.get(action));
            return managerCommand.execute(request, resp);
        }
        return null;
    }
}
