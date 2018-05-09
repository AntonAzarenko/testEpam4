package com.azarenko.servlets.servletcommands;

public class CommandException extends Exception {
    public CommandException(Throwable ex){
        super(ex);
    }
}
