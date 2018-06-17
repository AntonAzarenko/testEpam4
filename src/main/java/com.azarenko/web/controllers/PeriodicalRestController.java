package com.azarenko.web.controllers;

import com.azarenko.dao.DaoException;
import com.azarenko.model.Periodical;
import com.azarenko.services.PeriodicalService;
import com.azarenko.services.ServiceException;
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
        try {
            LOG.info("go to service");
            return periodicalService.getCatalog();
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void add(Periodical periodical) {
        try {
             periodicalService.add(periodical);

        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }

}
