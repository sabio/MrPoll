package com.mrpoll.controller;

import com.mrpoll.service.LoginService;
import com.mrpoll.utils.CommonMethods;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginController {
    private final String viewsdir = "login/";
    
    private LoginService loginService;
    
    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
    
    
    
    /**
     * This method handles login GET requests.
     * If users is logged-in and tries to goto login page again, will be redirected to index page.
     */
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String loginPage() {
        if (loginService.isCurrentAuthenticationAnonymous()) {
            return viewsdir+"login";
        } else {
            return "redirect:/";  
        }
    }
    
    /**
     * This method handles Access-Denied redirect.
     */
    @RequestMapping(value = "/access_denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
            model.addAttribute("loggedinuser", loginService.getPrincipalUsername());
           return viewsdir+"accessDenied";
    }

    /**
     * This method handles logout requests.
     * @param request
     * @param response
     */
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response){
        loginService.logout(request, response);
        return "redirect:/login?logout";
    }
}