package com.azarenko.servlets.servletCommands;

public class CommandException extends Exception {
    public CommandException(Throwable ex){
        super(ex);
    }
}
