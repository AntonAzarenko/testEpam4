package com.azarenko.model;

import java.util.Date;

/**
 * Класс хранит информацию о пользователе: ID, имя, почту(используеться как LOGIN), пароль, enabled(авторизован
 * ли пользователь если осуществляет переход по страницам), дату регистрации.
 * @author Anton Azarenko
 */
public final class User extends AbstractBaseEntity {

    private final String name;

    private final String email;

    private final String password;

    private boolean enabled = true;

    private final String role;

    private  Date registered = new Date();

    private User(UserBulder userBulder) {
        this.name = userBulder.name;
        this.email = userBulder.email;
        this.password = userBulder.password;
        this.enabled = userBulder.enabled;
        this.role = userBulder.role;
        this.registered = userBulder.registered;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getRole() {
        return role;
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

    public static class UserBulder {
        private final int id;
        private String name;
        private String email;
        private String password;
        private String role;
        private boolean enabled;
        private Date registered;

        public UserBulder(int id) {
            this.id = id;
        }

        public UserBulder name(String name) {
            this.name = name;
            return this;
        }

        public UserBulder email(String email) {
            this.email = email;
            return this;
        }

        public UserBulder password(String password) {
            this.password = password;
            return this;
        }

        public UserBulder role(String role) {
            this.role = role;
            return this;
        }

        public UserBulder enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public UserBulder registered(Date date){
            this.registered = registered;
            return this;
        }

        public User build() {
            return new User(this);
        }

    }
}
