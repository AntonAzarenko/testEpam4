package com.azarenko.servlets.servletcommands.usercommands;

import com.azarenko.services.ServiceException;
import com.azarenko.servlets.servletcommands.Command;
import com.azarenko.servlets.servletcommands.CommandException;
import com.azarenko.servlets.servletcommands.admincommands.ShowCatalogPeriodicals;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class ShowCatalog implements Command{
    private final String CATALOG_LIST = "pages/user/user_catalog.jsp";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException, UnsupportedEncodingException {
        ShowCatalogPeriodicals showCatalog = new ShowCatalogPeriodicals();
        showCatalog.execute(request, resp);
        return CATALOG_LIST;
    }
}
