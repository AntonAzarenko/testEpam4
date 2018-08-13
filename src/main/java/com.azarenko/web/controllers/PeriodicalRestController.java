package com.azarenko.web.controllers;

import com.azarenko.model.Periodical;
import com.azarenko.services.PeriodicalService;
import com.azarenko.to.PeriodicalTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/admin/periodical")
public class PeriodicalRestController {
    private static final Logger LOG = LoggerFactory.getLogger(PeriodicalRestController.class);
    @Autowired
    private PeriodicalService service;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Periodical> getAll() {
        return service.getAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = ("/add"), consumes = MediaType.APPLICATION_JSON_VALUE)
    public void add(Periodical periodical) {
        service.save(periodical);
    }

    @RequestMapping(method = RequestMethod.POST, value = ("/archive/{id}"))
    public void setArchive(@PathVariable("id") int id) {
        service.setArchive(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search/{search}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Periodical search(@PathVariable("search") String search) {
        return service.search(search);
    }

    @RequestMapping(method = RequestMethod.POST, value = ("/{id}"), produces = MediaType.APPLICATION_JSON_VALUE)
    public Periodical get(@PathVariable("id") int id) {
        return service.get(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void update(PeriodicalTo periodicalTo) {
        service.save(periodicalTo.asPeriodical());
    }

}
