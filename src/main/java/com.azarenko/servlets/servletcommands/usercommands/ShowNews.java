package com.azarenko.servlets.servletcommands.usercommands;

import com.azarenko.exceptions.ServiceException;
import com.azarenko.servlets.servletcommands.Command;
import com.azarenko.servlets.servletcommands.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class ShowNews implements Command {
    private final Logger log = Logger.getLogger(ShowNews.class);
    private final String NEWS = "/pages/user/news.jsp";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException, UnsupportedEncodingException {
          return NEWS;
    }
}
