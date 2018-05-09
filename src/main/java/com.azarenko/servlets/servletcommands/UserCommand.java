package com.azarenko.servlets.servletcommands;

import com.azarenko.services.ServiceException;
import com.azarenko.servlets.servletcommands.usercommands.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

public class UserCommand implements Command {
    private static ConcurrentHashMap<String, Command> map = new ConcurrentHashMap<>();

    static {
        map.put("catalog", new ShowCatalog());
        map.put("myprofile", new ShowProfile());
        map.put("pay", new Pay());
        map.put("exit", new UserExit());
        map.put("myperiodicals", new ShowPeriodical());
        map.put("start", new StartWork());
        map.put("show", new ShowCurrentPeriodical());
        map.put("shoppingcart", new ShowShoppingCart());
        map.put("addtocart", new AddToShoppingCart());
        map.put("redirectsubscribe", new RedirectToSubscribe());
        map.put("page", new ShowCatalog());
        map.put("news", new ShowNews());
        map.put("profile", new Profile());
        map.put("showcurrentsubscription", new ShowCurrentSubscription());
    }

    private final Logger log = Logger.getLogger(UserCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException, UnsupportedEncodingException {
        String forwardPage = request.getParameter("action");
        if (map.containsKey(forwardPage)) {
            ManagerCommand managerCommand = new ManagerCommand(map.get(forwardPage));
            String f = null;
            try {
                f = managerCommand.execute(request, resp);
            } catch (SQLException e) {
                log.error(e);
                request.setAttribute("error", "error");
            }
            return f;
        }
        return null;
    }
}
