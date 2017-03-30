package com.mrpoll.controller;

import com.mrpoll.model.Poll;
import com.mrpoll.service.AnswerPollService;
import com.mrpoll.service.PollService;
import com.mrpoll.service.UserService;
import com.mrpoll.validator.AnswerPollFormValidator;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
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
    
    
    @RequestMapping(value = {"/availablePolls"}, method = RequestMethod.GET)
    public String availablePolls(Model model){
        model.addAttribute("polls", pollService.getAvailablePolls());
        return viewsdir + "availablePolls";
    }
    
    @RequestMapping(value = {"/answerPoll/{uuid}"}, method = RequestMethod.GET)
    public String answerPoll(@PathVariable("uuid") String uuid, HttpServletRequest request, Model model){
        if(isPollAlreadyAnswered(uuid,request)){
            return "redirect:/pollList";
        }
        Poll poll = pollService.findByUUID(uuid);
        FormResponse formResponse = new FormResponse();
        model.addAttribute("formResponse", formResponse);
        model.addAttribute("poll", poll);
        return viewsdir + "answerPoll";
    }
    
    
    @RequestMapping(value = {"/answerPoll/{uuid}"}, method = RequestMethod.POST)
    public String answerPoll(@PathVariable("uuid") String uuid, @Valid FormResponse formResponse, BindingResult result, Model model, final RedirectAttributes redirectAttributes, Locale locale, HttpServletResponse response){
        Poll poll = pollService.findByUUID(uuid);
        model.addAttribute("poll", poll);
        if (result.hasErrors()) {
            return viewsdir + "answerPoll";
        }
        
        populateExtraInfo(formResponse);
        answerPollService.saveAnswers(formResponse); 
        response.addCookie(new Cookie(uuid,"1"));
        
        return viewsdir + "pollAnswered";
    }

    private void populateExtraInfo(FormResponse formResponse) {
        formResponse.setResponseDate(new Date());
    }
    
    private boolean isPollAlreadyAnswered(String uuid, HttpServletRequest request){
        
        return false;
        /*
        if(request.getCookies() == null) return false;
        
        for(Cookie c : request.getCookies()){
            if(c.getName().equals(uuid) && c.getValue().equals("1")) return true;
        }
        
        
        return false;
        */
    }
}
