package com.azarenko.servlets.servletCommands;

import com.azarenko.services.ServiceException;
import com.azarenko.servlets.servletCommands.registerCommand.Authorization;
import com.azarenko.servlets.servletCommands.registerCommand.RegistrationRedirect;
import com.azarenko.servlets.servletCommands.registerCommand.Registration;
import com.azarenko.servlets.servletCommands.registerOperation.ArrayOperationRegistration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ConcurrentHashMap;

public class CommandRegistration implements Command {
    private static ConcurrentHashMap<String, Command> map = new ConcurrentHashMap<>();
    static {
        map.put("authorize", new Authorization(new ArrayOperationRegistration()));
        map.put("register", new RegistrationRedirect(new ArrayOperationRegistration()));
        map.put("reg", new Registration(new ArrayOperationRegistration()));
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException, UnsupportedEncodingException {
        String action = request.getParameter("action");
        if(map.containsKey(action)){
            ManagerCommand managerCommand = new ManagerCommand(map.get(action));
            return managerCommand.execute(request, resp);
        }
        return null;
    }
}
