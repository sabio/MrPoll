package com.mrpoll.service;

import com.mrpoll.controller.FormUser;
import com.mrpoll.model.Role;
import com.mrpoll.model.User;
import java.util.List;
import org.springframework.data.domain.Page;


public interface UserService {
    public User findByUsername(String username);
    
    Role findRoleById(int roleId);
    
    Page<User> getUserListPage(Integer pageNumber, Integer pageSize);
    
    void saveUser(FormUser formUser);
    
    public List<Role> getRoles();

    public FormUser findById(Integer id);

    public void updateUser(FormUser formUser);
    
}
