package com.azarenko.web.controllers;

import com.azarenko.model.ShoppingCart;
import com.azarenko.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SubscriptionController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping(value = "cartAll")
    public String getAllbyID(Model model){
        int userID = 1;
        model.addAttribute("list",shoppingCartService.getAllByUserID(userID));
        return "shoppingCart";
    }
}
