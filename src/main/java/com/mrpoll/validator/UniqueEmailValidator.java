package com.mrpoll.validator;

import com.mrpoll.model.FormUser;
import com.mrpoll.model.User;
import com.mrpoll.service.UserService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;


public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, FormUser> {
    
    @Autowired
    private UserService userService;
    
    @Override
    public void initialize(UniqueEmail a) {
        //nothing to do here
    }

    @Override
    public boolean isValid(FormUser formUser, ConstraintValidatorContext cvc) {
        Integer id = formUser.getId();
        String email = formUser.getEmail();
        
        User user = userService.findByEmail(email);
        if(user != null && user.getId().equals(id)) return true;
        
        return user == null;
    }
    
}
