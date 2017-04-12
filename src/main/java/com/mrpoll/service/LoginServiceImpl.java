package com.mrpoll.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Service;


@Service("loginService")
public class LoginServiceImpl implements LoginService{
    
    private PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
    private AuthenticationTrustResolver authenticationTrustResolver;
    
    /**
     * LoginServiceImpl Constructor
     * @param persistentTokenBasedRememberMeServices
     * @param authenticationTrustResolver 
     */
    @Autowired
    public LoginServiceImpl(PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices, AuthenticationTrustResolver authenticationTrustResolver) {
        this.persistentTokenBasedRememberMeServices = persistentTokenBasedRememberMeServices;
        this.authenticationTrustResolver = authenticationTrustResolver;
    }
    
    /**
     * PersistentTokenBasedRememberMeServices setter
     * @param persistentTokenBasedRememberMeServices 
     */
    public void setPersistentTokenBasedRememberMeServices(PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices) {
        this.persistentTokenBasedRememberMeServices = persistentTokenBasedRememberMeServices;
    }
    
    /**
     * AuthenticationTrustResolver setter
     * @param authenticationTrustResolver 
     */
    public void setAuthenticationTrustResolver(AuthenticationTrustResolver authenticationTrustResolver) {
        this.authenticationTrustResolver = authenticationTrustResolver;
    }
    
    
    /**
     * This method returns true if users is already authenticated, else false.
     */
    @Override
    public boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }
    
    /**
     * This method returns the username of logged-in user.
     * @return The username of the logged-in user.
     */
    @Override
    public String getPrincipalUsername(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
                userName = ((UserDetails)principal).getUsername();
        } else {
                userName = principal.toString();
        }
        return userName;
    }
    
    /**
     * This method, as its name says, logouts the user.
     * @param request
     * @param response
     */
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            //new SecurityContextLogoutHandler().logout(request, response, auth);
            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
    }
}
