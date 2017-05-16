package com.mrpoll.service;

import com.mrpoll.model.FormUser;
import com.mrpoll.entity.User;
import java.util.List;
import org.springframework.data.domain.Page;


public interface UserService {
    
    User findByUsername(String username);
    
    User findById(Integer id);
    
    User findByEmail(String username);
    
    List<User> findAll();
    
    Page<User> getUserListPage(Integer pageNumber, Integer pageSize);
    
    void saveFormUser(FormUser formUser);

    FormUser findFormUserById(Integer id);

    void updateFormUser(FormUser formUser);

    void deleteUserById(Integer id);
}
