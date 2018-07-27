package com.azarenko.model;


import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

import java.util.Collections;
import java.util.Date;
import java.util.Set;


/**
 * Класс хранит информацию о пользователе: ID, имя, почту(используеться как LOGIN), пароль, enabled(авторизован
 * ли пользователь если осуществляет переход по страницам), дату регистрации.
 *
 * @author Anton Azarenko
 */
@Entity
@Table(name = "users")
public class User extends AbstractBaseEntity {

    private static final String ALL_SORTED = "User.allSorted";
    private static final String DELETE = "User.delete";
    private static final String hj="";

    @Column(name = "name")
    private String name;
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    @Length(min = 5)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> authorities;
    @Column(name = "registration", columnDefinition = "timestamp default now()")
    private Date registered = new Date();

    public User(String name, String email, String password, boolean enabled, Role authorities, Date registered) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.authorities = Collections.singleton(authorities);
        this.registered = registered;
    }

    public User(Integer id, String name, String email, String password, boolean enabled, Role authorities, Date registered) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.authorities = Collections.singleton(authorities);
        this.registered = registered;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<Role> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) return false;
        if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null) return false;
        if (getPassword() != null ? !getPassword().equals(user.getPassword()) : user.getPassword() != null)
            return false;
        return getAuthorities() != null ? getAuthorities().equals(user.getAuthorities()) : user.getAuthorities() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getAuthorities() != null ? getAuthorities().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", authorities=" + authorities +
                ", registered=" + registered +
                '}';
    }
}
