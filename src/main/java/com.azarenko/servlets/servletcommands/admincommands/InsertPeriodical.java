package com.azarenko.servlets.servletcommands.admincommands;

import com.azarenko.exceptions.ServiceException;
import com.azarenko.servlets.servletcommands.Command;
import com.azarenko.servlets.servletcommands.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertPeriodical implements Command {
    private final String INSERT_OR_EDIT = "/pages/admin/admin_add_edit_publications.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException {
        return INSERT_OR_EDIT;
    }
}
