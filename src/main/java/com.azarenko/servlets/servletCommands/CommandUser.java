package com.azarenko.servlets.servletCommands;

import com.azarenko.services.ServiceException;
import com.azarenko.servlets.servletCommands.adminOperations.ArrayOperationPeriodical;
import com.azarenko.servlets.servletCommands.userCommand.*;
import com.azarenko.servlets.servletCommands.userOperations.ArrayOperationShoppingCart;
import com.azarenko.servlets.servletCommands.userOperations.ArrayOperationSubscription;
import com.azarenko.servlets.servletCommands.userOperations.ArrayOperationUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ConcurrentHashMap;

public class CommandUser implements Command {
    private static ConcurrentHashMap<String,Command> map = new ConcurrentHashMap<>();
    static {
        map.put("catalog", new ShowCatalog(new ArrayOperationPeriodical()));
        map.put("myprofile",new ShowProfile(new ArrayOperationUser()));
        map.put("subscribe",new Subscribe(new ArrayOperationSubscription()));
        map.put("exit", new UserExit(new ArrayOperationUser()));
        map.put("myperiodicals",new ShowPeriodical(new ArrayOperationSubscription()));
        map.put("start", new StartWork(new ArrayOperationUser()));
        map.put("show", new ShowCurrentPeriodical(new ArrayOperationPeriodical()));
        map.put("shopingcart", new ShowShoppingCart(new ArrayOperationShoppingCart()));
        map.put("addtocart", new AddToShoppingCart(new ArrayOperationShoppingCart()));
        map.put("redirectsubscribe", new RedirectToSubscribe(new ArrayOperationPeriodical()));
        map.put("page", new ShowCatalog(new ArrayOperationPeriodical()));
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
