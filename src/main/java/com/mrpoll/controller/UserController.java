package com.mrpoll.controller;

import com.mrpoll.service.UserService;
import com.mrpoll.utils.Constants;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final String viewsdir = "user"+java.io.File.separator;

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/userList"}, method = RequestMethod.GET)
    public String pollList(
            ModelMap model,
            @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_NUMBER) Integer pageNumber,
            @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_SIZE) Integer pageSize
    ) {

        Page userListPage = userService.getUserListPage(pageNumber - 1, pageSize);

        int totalPages = userListPage.getTotalPages();
        int currentIndex = userListPage.getNumber() + 1;
        int beginIndex = Math.max(1, currentIndex - 3);
        int endIndex = Math.min(beginIndex + 5, userListPage.getTotalPages());

        model.addAttribute("list", userListPage.getContent());
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentIndex", currentIndex);
        model.addAttribute("beginIndex", beginIndex);
        model.addAttribute("endIndex", endIndex);
        model.addAttribute("pageSize", pageSize);
        return viewsdir + "userList";

    }
    
    /*
    @ModelAttribute("formUser")
    public FormUser createFormUser() {
            return new FormUser();
    }
    */
    

    @RequestMapping(value = {"/addUser"}, method = RequestMethod.GET)
    public String addUser(Model model) {
        model.addAttribute("formUser", new FormUser());
        return viewsdir + "userForm";
    }
    
    @RequestMapping(value = {"/addUser"}, method = RequestMethod.POST)
    public String addUser(@Valid FormUser formUser, BindingResult result, Model model) {
        
        System.out.println("Errors = "+result.hasErrors());
        
        return viewsdir + "userForm";
    }
}
