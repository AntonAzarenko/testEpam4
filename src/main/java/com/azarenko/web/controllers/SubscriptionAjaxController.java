package com.azarenko.web.controllers;

import com.azarenko.model.Subscription;
import com.azarenko.services.SubscriptionService;
import com.azarenko.to.SubscriptionTo;
import com.azarenko.web.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/subscription")
public class SubscriptionAjaxController {

    @Autowired
    private SubscriptionService subscriptionService;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SubscriptionTo> getSubscriptionByUserID(){
        List<Subscription> list = subscriptionService.getAllByUserId(LoggedUser.getId());
       return subscriptionService.getAllUByUserId(list);
    }
}
