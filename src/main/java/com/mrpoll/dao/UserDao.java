package com.mrpoll.dao;

import com.mrpoll.model.Role;
import com.mrpoll.model.User;
import java.util.List;


public interface UserDao {
    User findById(Integer id);
    
    User findByUsername(String username);

    public void save(User user);
    
    public void update(User user);
    
    public boolean passwordHasChanged(Integer userId, String newPassword);
}
