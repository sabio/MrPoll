package com.mrpoll.controller;

import com.mrpoll.service.PollService;
import com.mrpoll.utils.CommonMethods;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
public class PollController {
    
    private final String viewsdir = "poll/";
    
    @Autowired
    private PollService pollService;
    
    @RequestMapping(value = {"/pollList"}, method = RequestMethod.GET)
    public String pollList(ModelMap model, HttpServletRequest request) {
        
        Integer pageNumber = CommonMethods.stringToInteger(request.getParameter("pageNumber"), null);
        Integer pageSize = CommonMethods.stringToInteger(request.getParameter("pageSize"), null);
        
        model.addAttribute("list", pollService.getPagePollList(pageNumber, pageSize).getContent());
        return viewsdir+"pollList";
    }
    
    
    
    
}
