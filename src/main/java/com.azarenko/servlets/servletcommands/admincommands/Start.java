package com.azarenko.servlets.servletcommands.admincommands;

import com.azarenko.services.ServiceException;
import com.azarenko.servlets.servletcommands.Command;
import com.azarenko.servlets.servletcommands.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public class Start implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException, UnsupportedEncodingException, SQLException {
        return "/pages/admin/admin_start_page.jsp";
    }
}
