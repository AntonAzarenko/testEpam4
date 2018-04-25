package com.azarenko.servlets.servletCommands;

import com.azarenko.services.ServiceException;
import com.azarenko.servlets.servletCommands.adminOperations.ArrayOperationPeriodical;
import com.azarenko.servlets.servletCommands.userCommand.ShowCatalog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ConcurrentHashMap;

public class CommandUser implements Command {
    private static ConcurrentHashMap<String,Command> map = new ConcurrentHashMap<>();
    static {

            map.put("catalog", new ShowCatalog(new ArrayOperationPeriodical()));

    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException, UnsupportedEncodingException {
      String forward = request.getParameter("action");
      if(map.containsKey(forward)){
          ManagerCommand managerCommand = new ManagerCommand(map.get(forward));
          String f = null;
          f = managerCommand.execute(request, resp);
          return f;
      }
      return null;
    }

}
