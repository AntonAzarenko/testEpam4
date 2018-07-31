package com.azarenko.web.controllers;

import com.azarenko.exceptions.NotFoundException;
import com.azarenko.model.User;
import com.azarenko.services.UserService;
import com.azarenko.services.logic.AuthorisationService;
import com.azarenko.to.UserTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/ajax/admin/users")
public class AdminAjaxController {
    @Autowired
    private UserService service;

    @Autowired
    private AuthorisationService authService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<User> getAll() {
        return service.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void add(UserTo userTo) {
        if (userTo.getId() == 0) {
            service.add(userTo.asUser());
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login/")
    public void login(@RequestParam(required = false) @PathVariable("emailE") String emailE,
                        @RequestParam(required = false) @PathVariable("passwordE") String passwordE) {
        if (authService.authorizeUser(emailE, passwordE)) {

        }else throw new NotFoundException("User has not been found");

    }


}
