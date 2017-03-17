package com.mrpoll.controller;

import com.mrpoll.model.Choice;
import com.mrpoll.model.Poll;
import com.mrpoll.model.Question;
import com.mrpoll.service.PollService;
import com.mrpoll.utils.Constants;
import com.mrpoll.validator.PollFormValidator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    PollFormValidator pollFormValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(pollFormValidator);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
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
        List<Question> qs = new ArrayList<>();
        List<Choice> cs = new ArrayList<>();
        
        Choice c = new Choice();
        c.setId(1);
        c.setChoiceText("Choice x");
        cs.add(c);cs.add(c);
        
        Question q = new Question();
        q.setId(1);
        q.setQuestionText("Question x");
        q.setChoices(cs);
        
        qs.add(q);qs.add(q);
        
        p.setQuestions(qs);
        
        
        model.addAttribute("poll", p);
        return viewsdir + "pollForm";
    }

    @RequestMapping(value = "/addPoll", method = RequestMethod.POST)
    public String addPoll(@ModelAttribute("poll") @Validated Poll poll, BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
    //public String addPoll(javax.servlet.http.HttpServletRequest req){
        
        //System.out.println("PAr = "+req.getParameter("expirationDate"));
        
        
        
        if (result.hasErrors()) {
            //populateDefaultModel(model);
            return viewsdir + "pollForm";
        } else {

            return "redirect:/pollList";

        }
        
    }
}
