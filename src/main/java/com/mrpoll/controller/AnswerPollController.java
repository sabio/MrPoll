package com.mrpoll.controller;

import com.mrpoll.model.Poll;
import com.mrpoll.service.AnswerPollService;
import com.mrpoll.service.PollService;
import com.mrpoll.service.UserService;
import com.mrpoll.validator.AnswerPollFormValidator;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AnswerPollController {
    @Autowired
    private PollService pollService;
    
    @Autowired
    private AnswerPollService answerPollService;
    
    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    private AnswerPollFormValidator answerPollFormValidator;
    
    private final String viewsdir = "answerPoll" + java.io.File.separator;
    
    
    @InitBinder("formResponse")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(answerPollFormValidator);
    }
    
    @RequestMapping(value = {"/answerPoll/{id}"}, method = RequestMethod.GET)
    public String answerPoll(@PathVariable("id") Integer id, Model model){
        Poll poll = pollService.findById(id);
        FormResponse formResponse = new FormResponse();
        model.addAttribute("formResponse", formResponse);
        model.addAttribute("poll", poll);
        return viewsdir + "answerPoll";
    }
    
    
    @RequestMapping(value = {"/answerPoll/{id}"}, method = RequestMethod.POST)
    public String answerPoll(@PathVariable("id") Integer id, @Valid FormResponse formResponse, BindingResult result, Model model, final RedirectAttributes redirectAttributes, Locale locale){
        if (result.hasErrors()) {
            Poll poll = pollService.findById(id);
            model.addAttribute("poll", poll);
            return viewsdir + "answerPoll";
        }
        
        populateExtraInfo(formResponse);
        answerPollService.saveAnswers(formResponse);
        /*
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", messageSource.getMessage("poll.added", null, locale));
        */  
        
        return "redirect:/pollList";
    }

    private void populateExtraInfo(FormResponse formResponse) {
        formResponse.setResponseDate(new Date());
    }
}
