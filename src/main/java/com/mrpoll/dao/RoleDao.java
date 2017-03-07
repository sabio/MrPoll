package com.mrpoll.dao;

import com.mrpoll.model.Role;
import java.util.List;


public interface RoleDao {
    Role findById(Integer roleId);
    
    List<Role> getRoles();
}
