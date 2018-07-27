package com.azarenko.web.controllers;

import com.azarenko.model.User;
import com.azarenko.services.UserService;
import com.azarenko.to.UserTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;
import java.util.Collections;

@Controller
@RequestMapping("ajax/admin/users")
public class AdminAjaxController {
    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<User> getAll() {
        return service.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void add(UserTo userTo){
        if(userTo.getId()==0){
            service.add(userTo.asUser());
        }
    }



}
