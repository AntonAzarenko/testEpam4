package com.azarenko.servlets.servletcommands.registercommands;

import com.azarenko.services.ServiceException;
import com.azarenko.servlets.servletcommands.Command;
import com.azarenko.servlets.servletcommands.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public class Identification implements Command {
    private final static Logger log = Logger.getLogger(Identification.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException, UnsupportedEncodingException, SQLException {
        HttpSession session = request.getSession();
        if(session.getAttribute("login")== null){
            log.info(session.getAttribute("login"));
            return "/authorize?action=authorize";
        }else if (session.getAttribute("role").equals("USER")) {
            log.info(session.getAttribute("role"));
            return "/user?action=start";
        } else if (session.getAttribute("role").equals("ADMIN")){
            log.info(session.getAttribute("role"));
            return "/admin?action=start";
        }
        return "/authorize?action=authorize ";
    }
}
