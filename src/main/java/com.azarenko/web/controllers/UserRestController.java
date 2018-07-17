package com.azarenko.web.controllers;

import com.azarenko.model.User;
import com.azarenko.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/admin/user")
public class UserRestController {
    @Autowired
    private UserService service;

    public User getUserByEmail(String email){
       return service.getByEmail(email);
    }
}
