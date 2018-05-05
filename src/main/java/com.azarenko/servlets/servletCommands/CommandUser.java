package com.azarenko.servlets.servletCommands;

import com.azarenko.services.ServiceException;
import com.azarenko.servlets.servletCommands.adminOperations.ArrayOperationPeriodical;
import com.azarenko.servlets.servletCommands.userCommand.*;
import com.azarenko.servlets.servletCommands.userOperations.ArrayOperationNews;
import com.azarenko.servlets.servletCommands.userOperations.ArrayOperationShoppingCart;
import com.azarenko.servlets.servletCommands.userOperations.ArrayOperationSubscription;
import com.azarenko.servlets.servletCommands.userOperations.ArrayOperationUser;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ConcurrentHashMap;

public class CommandUser implements Command {
    private final Logger log = Logger.getLogger(CommandUser.class);
    private static ConcurrentHashMap<String,Command> map = new ConcurrentHashMap<>();
    static {
        map.put("catalog", new ShowCatalog(new ArrayOperationPeriodical()));
        map.put("myprofile",new ShowProfile(new ArrayOperationUser()));
        map.put("pay",new Pay(new ArrayOperationSubscription()));
        map.put("exit", new UserExit(new ArrayOperationUser()));
        map.put("myperiodicals",new ShowPeriodical(new ArrayOperationSubscription()));
        map.put("start", new StartWork(new ArrayOperationUser()));
        map.put("show", new ShowCurrentPeriodical(new ArrayOperationPeriodical()));
        map.put("shoppingcart", new ShowShoppingCart(new ArrayOperationShoppingCart()));
        map.put("addtocart", new AddToShoppingCart(new ArrayOperationShoppingCart()));
        map.put("redirectsubscribe", new RedirectToSubscribe(new ArrayOperationPeriodical()));
        map.put("page", new ShowCatalog(new ArrayOperationPeriodical()));
        map.put("news", new ShowNews(new ArrayOperationNews()));
        map.put("profile", new Profile(new ArrayOperationUser()));
        map.put("showcurrentsubscription",new ShowCurrentSubscription(new ArrayOperationSubscription()));
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException, UnsupportedEncodingException {
      String forward = request.getParameter("action");
      log.info(forward);
      if(map.containsKey(forward)){
          ManagerCommand managerCommand = new ManagerCommand(map.get(forward));
          String f = managerCommand.execute(request, resp);;
          return f;
      }
      return null;
    }
}
