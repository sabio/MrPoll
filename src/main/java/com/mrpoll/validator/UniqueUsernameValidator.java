package com.mrpoll.validator;

import com.mrpoll.controller.FormUser;
import com.mrpoll.model.User;
import com.mrpoll.service.UserService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;


public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, FormUser>{
    
    @Autowired
    private UserService userService;
    
    @Override
    public void initialize(final UniqueUsername constraintAnnotation) {
        //Nothing to do here
    }

    @Override
    public boolean isValid(FormUser formUser, ConstraintValidatorContext cvc) {
        Integer id = formUser.getId();
        String username = formUser.getUsername();
        
        User user = userService.findByUsername(username);
        if(user != null && user.getId().equals(id)) return true;
        
        return user == null;
    }
    
}
