package com.mrpoll.converter;

import com.mrpoll.model.Role;
import com.mrpoll.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoleIdToRoleConverter implements Converter<String, Role> {
    
    @Autowired
    private RoleService roleService;

    @Override
    public Role convert(String id) {
        try {
            return roleService.findById(Integer.valueOf(id));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
}
