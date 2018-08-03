package com.azarenko.web.controllers;

import com.azarenko.model.Periodical;
import com.azarenko.model.ShoppingCart;
import com.azarenko.services.ShoppingCartService;
import com.azarenko.to.ShoppingCartTo;
import com.azarenko.util.ShoppingCartUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user/shoppingCart")
public class SubscriptionAjaxController {

    @Autowired
    private ShoppingCartService cartService;

    @Autowired
    private ShoppingCartTo shoppingCartTo;

    @Autowired
    private ShoppingCartUtil util;

    @RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
    public void addIncart(@PathVariable("id") int id,
                          @PathVariable("time") int time) {
        ShoppingCart cart = util.create(id, time);
        cartService.add(cart);
    }

    @RequestMapping(value = "/save/", method = RequestMethod.POST)
    public void save(ShoppingCartTo cartTo){

    }

    @RequestMapping(value = ("/periodical/get/{id}"), method = RequestMethod.POST)
    public ShoppingCartTo getInfoForSubscription(@PathVariable("id") int id) {
        return shoppingCartTo.create(id);
    }
}
