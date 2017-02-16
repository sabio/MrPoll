package com.mrpoll.controller;

import com.mrpoll.service.PollService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
public class PollController {
    
    private final String viewsdir = "poll/";
    
    @Autowired
    private PollService pollService;
    
    @RequestMapping(value = {"/pollList/{pageNumber}"}, method = RequestMethod.GET)
    public String pollList(@PathVariable Integer pageNumber, ModelMap model) {
        
        model.addAttribute("list", pollService.getPollList(pageNumber).getContent());
        return viewsdir+"pollList";
    }
}
