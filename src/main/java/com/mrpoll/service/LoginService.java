package com.mrpoll.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface LoginService{
    
    String getPrincipalUsername();
    
    boolean isCurrentAuthenticationAnonymous();
    
    void logout(HttpServletRequest request, HttpServletResponse response);
}
