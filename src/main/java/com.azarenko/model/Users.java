package com.azarenko.model;

import java.util.Date;
import java.util.Set;

public class Users extends AbstractNamedEntity {
    private String email;

    private String password;

    private boolean enabled = true;

    private String role;

    private Integer subscription;

    private Date registered = new Date();

    public Users(Integer id, String name, String email, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.registered = new Date();
        this.role = role;
    }

    public Users(Integer id, String name, String email, String password, boolean enabled, String role,
                 Integer subscription, Date registered) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
        this.subscription = subscription;
        this.registered = registered;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getSubscription() {
        return subscription;
    }

    public void setSubscription(Integer subscription) {
        this.subscription = subscription;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }
}
