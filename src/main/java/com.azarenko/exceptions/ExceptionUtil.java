package com.azarenko.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionUtil {
    private static final Logger LOG = LoggerFactory.getLogger(ExceptionUtil.class);

    private ExceptionUtil() {
    }

    public static boolean check(boolean found, int id) {
        return check(found, "id=" + id);
    }

    public static boolean check(boolean found, String msg) {
        if (!found) throw new NotFoundException("Not found entity with " + msg);
        return found;
    }

    public static <T> T check(T obj, int id) {
        return check(obj, "id=" + id);
    }

    public static <T> T check(T object, String msg) {
        if (object == null) throw new NotFoundException("Not found entity with " + msg);
        return object;
    }
}
