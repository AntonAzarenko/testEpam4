package com.azarenko.web;

import com.azarenko.model.Role;

import java.util.Collections;
import java.util.Set;

public class LoggedUser {
    protected int id = 0;
    protected Set<Role> roles = Collections.singleton(Role.ROLE_USER);
    protected boolean enabled = false;
    private static LoggedUser LOGGED_USER = new LoggedUser();

    public int getId() {
        return id;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public static LoggedUser getLoggedUser() {
        return LOGGED_USER;
    }
}
