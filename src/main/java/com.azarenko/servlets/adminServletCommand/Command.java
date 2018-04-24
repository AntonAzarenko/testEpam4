package com.azarenko.servlets.adminServletCommand;

import com.azarenko.services.ServiceException;
import com.azarenko.services.TransactionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public interface Command {
    String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException, UnsupportedEncodingException;
}
