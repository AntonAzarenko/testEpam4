package com.azarenko.web.controllers;

import com.azarenko.model.Payment;
import com.azarenko.services.PaymentService;
import com.azarenko.to.PaymentTo;
import com.azarenko.web.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "user/payments")
public class PaymentAjaxController {

    @Autowired
    private PaymentService service;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PaymentTo> getPayments(){
        List<Payment> list = service.getPaymentByUserId(LoggedUser.getId());
        return service.getPayTo(list);
    }
}
