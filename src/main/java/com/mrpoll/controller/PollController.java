package com.mrpoll.controller;

import com.mrpoll.model.Choice;
import com.mrpoll.model.Poll;
import com.mrpoll.model.Question;
import com.mrpoll.model.User;
import com.mrpoll.service.PollService;
import com.mrpoll.service.UserService;
import com.mrpoll.utils.Constants;
import com.mrpoll.validator.PollFormValidator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PollController {

    private final String viewsdir = "poll" + java.io.File.separator;

    @Autowired
    private PollService pollService;
    
    @Autowired
    private UserService userService;

    @Autowired
    PollFormValidator pollFormValidator;
    
    @Autowired
    private MessageSource messageSource;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(pollFormValidator);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);

    }

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

    @RequestMapping(value = {"/addPoll"}, method = RequestMethod.GET)
    public String addUser(Model model) {
        Poll p = new Poll();
        model.addAttribute("poll", p);
        return viewsdir + "pollForm";
    }

    @RequestMapping(value = "/addPoll", method = RequestMethod.POST)
    public String addPoll(@ModelAttribute("poll") @Validated Poll poll, BindingResult result, Model model, final RedirectAttributes redirectAttributes, Locale locale) {
        if (result.hasErrors()) {
            //populateDefaultModel(model);
            return viewsdir + "pollForm";
        } else {
            populateExtraInfo(poll);
            pollService.savePoll(poll);
            redirectAttributes.addFlashAttribute("css", "success");
            redirectAttributes.addFlashAttribute("msg", messageSource.getMessage("poll.added", null, locale));
            
            return "redirect:/pollList";
        }
    }
    
    @RequestMapping(value = {"/editPoll/{id}"}, method = RequestMethod.GET)
    public String editPoll(@PathVariable("id") Integer id, Model model) {
        Poll poll = pollService.findById(id);
        model.addAttribute("poll", poll);
        return viewsdir + "pollForm";
    }
    
    @RequestMapping(value = {"/editPoll"}, method = RequestMethod.POST)
    public String editPoll(@Valid Poll poll, BindingResult result, Model model, final RedirectAttributes redirectAttributes, Locale locale) {
        if (result.hasErrors()) {
            return viewsdir + "pollForm";
        }
        populateExtraInfo(poll);
        pollService.updatePoll(poll);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", messageSource.getMessage("poll.updated", null, locale));
        
        return "redirect:/pollList";
    }


    private void populateExtraInfo(Poll poll) {
        //Find logged user and set it in poll
        if(poll.getId() == null){
            UserDetails principal = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userService.findByUsername(principal.getUsername());
            poll.setUserId(user);
        }
        else{
            poll.setUserId(pollService.getPollOwner(poll.getId()));
        }
        
    }
}
