package com.mrpoll.dao;

import com.mrpoll.entity.Role;
import java.util.List;


public interface RoleDao {
    Role findById(Integer roleId);
    
    List<Role> getRoles();
}
