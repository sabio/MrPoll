package com.mrpoll.controller;

import com.mrpoll.model.Role;
import com.mrpoll.service.UserService;
import com.mrpoll.utils.Constants;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private final String viewsdir = "user" + java.io.File.separator;
    
    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    private LocaleResolver localeResolver;

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

    @ModelAttribute("roles")
    public List<Role> createFormUser() {
        return userService.getRoles();
    }

    @RequestMapping(value = {"/addUser"}, method = RequestMethod.GET)
    public String addUser(Model model) {
        model.addAttribute("formUser", new FormUser());
        return viewsdir + "userForm";
    }
    
    @RequestMapping(value = {"/editUser/{id}"}, method = RequestMethod.GET)
    public String editUser(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("formUser", userService.findById(id));
        return viewsdir + "userForm";
    }
    
    @RequestMapping(value = {"/addUser"}, method = RequestMethod.POST)
    public String addUser(@Valid FormUser formUser, BindingResult result, Model model, final RedirectAttributes redirectAttributes, Locale locale) {
        for(ObjectError obj : result.getAllErrors()){
            System.out.println("++Error = "+obj.getClass());
        }
        if (result.hasErrors()) {
            return viewsdir + "userForm";
        }
        
        userService.saveUser(formUser);
        redirectAttributes.addFlashAttribute("css", "success");
        //Locale locale = localeResolver.resolveLocale(request);
        redirectAttributes.addFlashAttribute("msg", messageSource.getMessage("user.added", null, locale));
        return "redirect:/userList";
    }
    
    @RequestMapping(value = {"/editUser"}, method = RequestMethod.POST)
    public String editUser(@Valid FormUser formUser, BindingResult result, Model model, final RedirectAttributes redirectAttributes, Locale locale) {
        if (result.hasErrors()) {
            return viewsdir + "userForm";
        }
        userService.updateUser(formUser);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", messageSource.getMessage("user.updated", null, locale));
        return "redirect:/userList";
    }
}
