package com.azarenko.web.controllers;

import com.azarenko.model.Periodical;
import com.azarenko.services.PeriodicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
@RequestMapping("/ajax/periodical")
public class PeriodicalAjaxController {
    @Autowired
    private PeriodicalService service;

    @RequestMapping(method = RequestMethod.POST)
    public void udate(@RequestParam("id") int id,
                      @RequestParam("title") String title,
                      @RequestParam("discription") String discription,
                      @RequestParam("publisher") String publisher,
                      @RequestParam("index") int index,
                      @RequestParam("output_frequency") int output_frequency,
                      @RequestParam("Age_limit") int ageLimit,
                      @RequestParam("price") BigDecimal price) {
        Periodical periodical = new Periodical(id, title, discription, publisher, output_frequency, index, ageLimit, price);
        service.save(periodical);
        }
}
