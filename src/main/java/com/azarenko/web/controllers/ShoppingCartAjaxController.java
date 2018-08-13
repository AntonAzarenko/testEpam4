package com.azarenko.web.controllers;


import com.azarenko.model.ShoppingCart;
import com.azarenko.services.ShoppingCartService;
import com.azarenko.services.SubscriptionService;
import com.azarenko.to.ShoppingCartShowTo;
import com.azarenko.to.ShoppingCartTo;
import com.azarenko.util.ShoppingCartUtil;

import com.azarenko.web.LoggedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user/shoppingCart")
public class ShoppingCartAjaxController {
    private static final Logger LOG = LoggerFactory.getLogger(PeriodicalRestController.class);


    @Autowired
    private ShoppingCartService cartService;

    @Autowired
    private ShoppingCartTo shoppingCartTo;

    @Autowired
    private ShoppingCartUtil util;

    @Autowired
    private SubscriptionService subscriptionService;


    @RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
    public void addIncart(@PathVariable("id") int id,
                          @PathVariable("time") int time) {
        ShoppingCart cart = util.create(id, time);
        cartService.add(cart);
    }

    @RequestMapping(value = "/periodical/save/", method = RequestMethod.POST)
    public void save(ShoppingCartTo cartTo) {
        cartService.add(cartService.checkAndCreate(cartTo));
    }

    @RequestMapping(value = ("/periodical/get/{id}"), method = RequestMethod.POST)
    public ShoppingCartTo getInfoForSubscription(@PathVariable("id") int id) {
        return shoppingCartTo.create(id);
    }

    @RequestMapping(value = ("/check/get/"), method = RequestMethod.GET)
    public void checkGet() {
        List<ShoppingCart> list = cartService.getAllByUserID(LoggedUser.getId());
        if (list.size() == 0) throw new com.azarenko.exceptions.NotFoundException("Shopping cart is Empty");
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ShoppingCartShowTo> getAll() {
        return cartService.getAllTO(LoggedUser.getId());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/pay/")
    public void pay(){
       subscriptionService.add(LoggedUser.getId());
    }
}
