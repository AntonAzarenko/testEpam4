package com.azarenko.to;

import com.azarenko.model.Role;
import com.azarenko.model.User;
import org.hibernate.validator.constraints.Email;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Size;
import java.util.Date;

public class UserTo {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Nullable
    private String name;
    @Nullable
    @Email
    private String email;
    @Size(min = 5, max = 15, message = "must between 5 and 15 character")
    private String password;

    public User asUser() {
        return new User(null, name, email, password, true, Role.ROLE_USER, new Date());
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
}
