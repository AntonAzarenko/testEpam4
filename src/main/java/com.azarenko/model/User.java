package com.azarenko.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.Date;
import java.util.Set;


/**
 * Класс хранит информацию о пользователе: ID, имя, почту(используеться как LOGIN), пароль, enabled(авторизован
 * ли пользователь если осуществляет переход по страницам), дату регистрации.
 *
 * @author Anton Azarenko
 */

public  class User extends AbstractBaseEntity {

    private String name;

    private String email;

    private String password;

    private boolean enabled = true;

    private Set<Role> authirities;

    private Date registered = new Date();

    public User() {

    }



}
