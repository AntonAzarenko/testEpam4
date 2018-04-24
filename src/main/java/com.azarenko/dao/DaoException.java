package com.azarenko.dao;


import org.apache.log4j.Logger;

public class DaoException extends Exception {
    private final Logger log = Logger.getLogger(DaoException.class);
    public DaoException(Throwable cause) {
        super(cause);
       // log.debug(cause);
    }
}
