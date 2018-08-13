package com.azarenko.web.controllers;

import com.azarenko.model.ShoppingCart;
import com.azarenko.services.ShoppingCartService;
import com.azarenko.to.ShoppingCartShowTo;
import com.azarenko.web.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping(value = "cartAll")
    public String getAllbyID(Model model){
        List<ShoppingCartShowTo> list = shoppingCartService.getAllTO(LoggedUser.getId());
        model.addAttribute("price",shoppingCartService.getCost(list));
        return "shoppingCart";
    }
}
