package com.azarenko.model;

import java.util.Date;

public class Users extends AbstractNamedEntity {
    private String email;

    private String password;

    private boolean enabled = true;

    private String role;

    private String subscription;

    private Date registered = new Date();

    public Users(Integer id, String name, String email, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.registered = new Date();
        this.role = role;
        subscription = "";
    }

    public Users(Integer id, String name, String email, String password, boolean enabled, String role,
                 String subscription, Date registered) {
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

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    @Override
    public String toString() {
        return "Users{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", role='" + role + '\'' +
                ", subscription='" + subscription + '\'' +
                ", registered=" + registered +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
