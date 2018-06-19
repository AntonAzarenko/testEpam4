package com.azarenko.web.controllers;

import com.azarenko.model.Periodical;
import com.azarenko.services.PeriodicalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PeriodicalRestController {
    private static final Logger LOG = LoggerFactory.getLogger(PeriodicalRestController.class);
    @Autowired
    private PeriodicalService periodicalService;

    public List<Periodical> getListEntity() {
        LOG.info("go to service");
        List<Periodical> list = periodicalService.getAll();
        return periodicalService.sortByName(list);
    }

    public void add(Periodical periodical) {
        periodicalService.save(periodical);
    }

    public void remove(int id){
       if(periodicalService.remove(id)){
           LOG.info("Remove status is OK");
       }else LOG.info("Entity was not delete");
    }

    public Periodical search(String param, String value){
        return periodicalService.search(param,value);
    }

    public Periodical get(int id){
        return periodicalService.get(id);
    }

}
