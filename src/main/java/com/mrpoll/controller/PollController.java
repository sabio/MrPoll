package com.mrpoll.controller;

import com.mrpoll.service.PollService;
import com.mrpoll.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PollController {

    private final String viewsdir = "poll/";

    @Autowired
    private PollService pollService;

    @RequestMapping(value = {"/pollList"}, method = RequestMethod.GET)
    public String pollList(
            ModelMap model,
            @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_NUMBER) Integer pageNumber,
            @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_SIZE) Integer pageSize
    ) {
        
        Page pollListPage = pollService.getPollListPage(pageNumber - 1, pageSize);
        
        int totalPages = pollListPage.getTotalPages();
        int currentIndex = pollListPage.getNumber() + 1;
        int beginIndex = Math.max(1, currentIndex - 3);
        int endIndex = Math.min(beginIndex + 5, pollListPage.getTotalPages());
        
        
        model.addAttribute("list", pollListPage.getContent());
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentIndex", currentIndex);
        model.addAttribute("beginIndex", beginIndex);
        model.addAttribute("endIndex", endIndex);
        model.addAttribute("pageSize", pageSize);
        return viewsdir + "pollList";
    }

}
