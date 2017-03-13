package com.mrpoll.service;

import com.mrpoll.model.Role;
import java.util.List;


public interface RoleService {
    
    Role findRoleById(Integer roleId);
    
    List<Role> getRoles();
}
