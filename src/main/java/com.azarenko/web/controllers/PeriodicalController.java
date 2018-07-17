package com.azarenko.web.controllers;

import com.azarenko.model.Periodical;
import com.azarenko.services.PeriodicalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class PeriodicalController {

    private static final Logger LOG = LoggerFactory.getLogger(PeriodicalController.class);
    @Autowired
    private PeriodicalService service;

    @GetMapping(value = "start")
    public String periodicalList(Model model){
        model.addAttribute("list",service.getAll());
        LOG.info("Get all");
        return "list";
    }

    @GetMapping(value = "edit")
    public String edit(@RequestParam(required = false)@PathVariable("id")int id, Model model){
        model.addAttribute("periodical", service.get(id));
        LOG.info("Edit periodical "+ id);
        return "add_edit_publications";

    }

    @PostMapping(value = "save")
    public String save(@ModelAttribute("periodical") Periodical periodical){
        LOG.info("Save periodical...");
        service.save(periodical);
        return "redirect:/start";
    }

    @GetMapping(value = "add")
    public String add(@ModelAttribute("periodical")Periodical periodical){
        LOG.info("Add periodical...");
        return "add_edit_publications";
    }

    @GetMapping(value = "archive")
    public String archive(@RequestParam(required = false)@PathVariable("id")int id){
        LOG.info("set archive...");
        service.setArchive(id);
        return "redirect:/start";
    }

    @GetMapping(value = "sortIndex")
    public String sortByIndex(){
        LOG.info("sort by index...");
        service.sortByIndex(service.getAll());
        return "redirect:/start";
    }

    @GetMapping(value = "sortTitle")
    public String sortByTitle(){
        LOG.info("sort by title...");
        service.sortByName(service.getAll());
        return "redirect:/start";
    }

    @PostMapping(value = "search")
    public String search(@RequestParam(required = false)@PathVariable("search")String search, Model model){
        Periodical periodical = service.search(search);
        if(periodical == null){
            model.addAttribute("message","This periodical is not found");
            return "redirect:/start";
        }else {
            model.addAttribute("periodical", periodical);
        }
        return "add_edit_publications";
    }
}
