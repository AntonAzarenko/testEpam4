package com.azarenko.servlets.servletcommands.admincommands;

import com.azarenko.exceptions.ServiceException;
import com.azarenko.servlets.servletcommands.Command;
import com.azarenko.servlets.servletcommands.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public class Start implements Command {
    private final Logger log = Logger.getLogger(Start.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException, UnsupportedEncodingException, SQLException {
        log.info(request.getContextPath());
        return "pages/admin/admin_start_page.jsp";
    }
}
