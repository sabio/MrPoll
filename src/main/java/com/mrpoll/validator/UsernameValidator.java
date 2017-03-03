package com.mrpoll.validator;

import com.mrpoll.service.UserService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;


public class UsernameValidator implements ConstraintValidator<Username, String>{
    
    @Autowired
    private UserService userService;
    
    @Override
    public void initialize(Username a) {
        //Nothing to do here
    }
    

    @Override
    public boolean isValid(String username, ConstraintValidatorContext cvc) {
        return userService.findByUsername(username) == null;
    }
    
}
