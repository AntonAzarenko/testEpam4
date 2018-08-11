package com.azarenko.web;

import com.azarenko.model.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;
import java.util.Set;

public class LoggedUser {
    protected int id = 0;

    protected Set<Role> roles = Collections.singleton(Role.ROLE_USER);

    protected boolean enabled = false;

    private static LoggedUser LOGGED_USER = new LoggedUser();

    public static LoggedUser safeGet(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null){
            return null;
        }
        Object user = auth.getPrincipal();
        return (user instanceof LoggedUser) ? (LoggedUser) user : null;
    }
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
