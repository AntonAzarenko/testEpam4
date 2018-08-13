package com.azarenko.web.controllers;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SubscriptionController {

    @GetMapping(value = "subscriptions")
    public String get(Model model){
        return "subscription";
    }
}
