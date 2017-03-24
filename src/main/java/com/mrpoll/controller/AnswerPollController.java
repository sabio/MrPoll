package com.mrpoll.controller;

import com.mrpoll.model.Poll;
import com.mrpoll.service.PollService;
import com.mrpoll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AnswerPollController {
    @Autowired
    private PollService pollService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private MessageSource messageSource;
    
    private final String viewsdir = "answerPoll" + java.io.File.separator;
    
    @RequestMapping(value = {"/answerPoll/{id}"}, method = RequestMethod.GET)
    public String answerPoll(@PathVariable("id") Integer id, Model model){
        Poll poll = pollService.findById(id);
        FormResponse formResponse = new FormResponse();
        model.addAttribute("poll", poll);
        model.addAttribute("formResponse", formResponse);
        return viewsdir + "answerPoll";
    }
    
    
    @RequestMapping(value = {"/answerPoll/{id}"}, method = RequestMethod.POST)
    public String answerPoll(@PathVariable("id") Integer id, FormResponse formResponse, Model model){
        Poll poll = pollService.findById(id);
        FormResponse response = new FormResponse();
        model.addAttribute("poll", poll);
        model.addAttribute("formResponse", formResponse);
        return viewsdir + "answerPoll";
    }
}
