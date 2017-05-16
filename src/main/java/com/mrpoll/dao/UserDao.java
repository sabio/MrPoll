package com.mrpoll.dao;

import com.mrpoll.entity.Role;
import com.mrpoll.entity.User;
import java.util.List;


public interface UserDao {
    User findById(Integer id);
    
    User findByUsername(String username);
    
    List<User> findAll();

    public void save(User user);
    
    public void update(User user);
    
    public void delete(User user);
    
    public boolean passwordHasChanged(Integer id, String newPassword);

    public void deleteUserById(Integer id);
}
