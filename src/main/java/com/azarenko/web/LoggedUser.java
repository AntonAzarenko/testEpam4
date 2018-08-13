package com.azarenko.web;

import com.azarenko.model.Role;
import com.azarenko.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Objects;
import java.util.Set;

public class LoggedUser implements UserDetails {

    protected User user;

    public LoggedUser(User user) {
        this.user = user;
    }

    public static LoggedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object user = auth.getPrincipal();
        return (user instanceof LoggedUser) ? (LoggedUser) user : null;
    }

    public static LoggedUser get() {
        LoggedUser user = safeGet();
        Objects.requireNonNull(user, "No autorized user found");
        return user;
    }

    public static int getId() {
        Object o = safeGet();
        return get().user.getId();
    }


    @Override
    public Set<Role> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isEnabled();
    }

    public boolean isEnabled() {
        return user.isEnabled();
    }


}
