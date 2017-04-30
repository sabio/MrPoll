package com.mrpoll.service;

import com.mrpoll.model.Role;
import java.util.List;


public interface RoleService {
    
    Role findById(Integer roleId);
    
    List<Role> getRoles();
}
