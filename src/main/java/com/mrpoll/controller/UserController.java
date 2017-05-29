package com.mrpoll.controller;

import com.mrpoll.exception.ResourceNotFoundException;
import com.mrpoll.model.FormUser;
import com.mrpoll.entity.Role;
import com.mrpoll.service.RoleService;
import com.mrpoll.service.UserService;
import com.mrpoll.utils.Constants;
import java.util.List;
import java.util.Locale;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private final String viewsdir = "user" + java.io.File.separator;
    
    @Autowired
    private MessageSource messageSource;
    
    /*
    @Autowired
    private LocaleResolver localeResolver;
    */
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleService roleService;

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
        return roleService.getRoles();
    }

    @RequestMapping(value = {"/addUser"}, method = RequestMethod.GET)
    public String addUser(Model model) {
        model.addAttribute("formUser", new FormUser());
        return viewsdir + "userForm";
    }
    
    @RequestMapping(value = {"/addUser"}, method = RequestMethod.POST)
    public String addUser(@Valid FormUser formUser, BindingResult result, Model model, final RedirectAttributes redirectAttributes, Locale locale) {
        if (result.hasErrors()) {
            return viewsdir + "userForm";
        }
        
        userService.saveFormUser(formUser);
        redirectAttributes.addFlashAttribute("css", "success");
        //Locale locale = localeResolver.resolveLocale(request);
        redirectAttributes.addFlashAttribute("msg", messageSource.getMessage("user.added", null, locale));
        return "redirect:/userList";
    }
    
    @RequestMapping(value = {"/editUser/{id}"}, method = RequestMethod.GET)
    public String editUser(@PathVariable("id") Integer id, Model model) {
        FormUser formUser = userService.findFormUserById(id);
        
        if(formUser == null) throw new ResourceNotFoundException(); 
        
        model.addAttribute("formUser", formUser);
        return viewsdir + "userForm";
    }
    
    @RequestMapping(value = {"/editUser"}, method = RequestMethod.POST)
    public String editUser(@Valid FormUser formUser, BindingResult result, Model model, final RedirectAttributes redirectAttributes, Locale locale) {
        if (result.hasErrors()) {
            return viewsdir + "userForm";
        }
        userService.updateFormUser(formUser);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", messageSource.getMessage("user.updated", null, locale));
        return "redirect:/userList";
    }
    
    @RequestMapping(value = {"/deleteUser/{id}"}, method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") Integer id, final RedirectAttributes redirectAttributes, Locale locale) {
        userService.deleteUserById(id);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", messageSource.getMessage("user.deleted", null, locale));
        return "redirect:/userList";
    }
    
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFoundException() {
        return viewsdir + "userNotFound";
    }
}
