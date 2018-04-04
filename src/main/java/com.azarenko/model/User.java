package com.azarenko.model;

import java.util.Date;

/**
 * Класс хранит информацию о пользователе: ID, имя, почту(используеться как LOGIN), пароль, enabled(авторизован
 * ли пользователь если осуществляет переход по страницам), дату регистрации.
 */
public class User extends AbstractBaseEntity {

    private String name;

    private String email;

    private String password;

    private boolean enabled = true;

    private String role;


    private Date registered = new Date();
    //The constructor for registration new user
    public User(Integer id, String name, String email, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.registered = new Date();
        this.role = role;
    }

    public User() {
    }



    //The constructor gets the user from the database
    public User(Integer id, String name, String email, String password, boolean enabled, String role,
                Date registered) {
        super(id);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
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

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", role='" + role + '\'' +
                ", registered=" + registered +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
