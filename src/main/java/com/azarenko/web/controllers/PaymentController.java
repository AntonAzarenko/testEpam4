package com.azarenko.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentController {

    @GetMapping(value = "payments")
    public String getPayments(Model model){
        return "paymentsUser";
    }
}
