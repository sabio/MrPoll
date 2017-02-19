package com.mrpoll.controller;

import com.mrpoll.service.PollService;
import com.mrpoll.utils.CommonMethods;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
public class PollController {

    private final String viewsdir = "poll/";

    @Autowired
    private PollService pollService;

    @RequestMapping(value = {"/pollList"}, method = RequestMethod.GET)
    public String pollList(
            ModelMap model,
            @RequestParam(required = false, defaultValue = "1") Integer pageNumber
            ,@RequestParam(required = false, defaultValue = "5") Integer pageSize
    ) {
        
        Page pollListPage = pollService.getPagePollList(pageNumber - 1, pageSize);
        
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
