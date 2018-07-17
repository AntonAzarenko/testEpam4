package com.azarenko.web.controllers;

import com.azarenko.services.PeriodicalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private PeriodicalService service;

    @GetMapping(value = "list")
    public String getAll(Model model){
        model.addAttribute("list", service.getAll());
        return "user";
    }

    @GetMapping(value = "get")
    public String get(@RequestParam(required = false)@PathVariable("id")int id, Model model){
        model.addAttribute("periodical", service.get(id));
        model.addAttribute("list", service.getAll());
        return "user";
    }

}
