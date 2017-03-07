package com.mrpoll.converter;

import com.mrpoll.model.Role;
import com.mrpoll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoleIdToRoleConverter implements Converter<String, Role> {
    
    @Autowired
    private UserService userService;

    @Override
    public Role convert(String id) {
        try {
            return userService.findRoleById(Integer.valueOf(id));
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
    
}
