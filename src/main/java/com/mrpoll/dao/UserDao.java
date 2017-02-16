package com.mrpoll.dao;

import com.mrpoll.model.Role;
import com.mrpoll.model.User;


public interface UserDao {
    User findByUsername(String username);
    
    Role findRoleById(int roleId);
    
}
