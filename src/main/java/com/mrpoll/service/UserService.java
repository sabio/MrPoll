package com.mrpoll.service;

import com.mrpoll.model.Role;
import com.mrpoll.model.User;
import org.springframework.data.domain.Page;


public interface UserService {
    public User findByUsername(String username);
    
    Role findRoleById(int roleId);
    
    Page<User> getUserListPage(Integer pageNumber, Integer pageSize);
}
