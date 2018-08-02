package com.azarenko.web.controllers;

import com.azarenko.model.Periodical;
import com.azarenko.services.PeriodicalService;
import com.azarenko.to.PeriodicalTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/ajax/periodical")
public class PeriodicalAjaxController {
    private static final Logger LOG = LoggerFactory.getLogger(PeriodicalController.class);

    @Autowired
    private PeriodicalService service;

    @RequestMapping(method = RequestMethod.POST)
    public void update(PeriodicalTo periodicalTo) {
        service.save(periodicalTo.asPeriodical());
        LOG.info("saveAjax");
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Periodical> getAll() {
        LOG.info("getAllAjax");
        return service.getAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = ("/archive/{id}"))
    public void setArchive(@PathVariable("id") int id) {
        service.setArchive(id);
        LOG.info("add in archive");
    }

    @RequestMapping(method = RequestMethod.GET, value = ("/get/{id}"), produces = MediaType.APPLICATION_JSON_VALUE)
    public Periodical get(@PathVariable("id") int id){
        LOG.info("getOne");
       return service.get(id);
    }
}
