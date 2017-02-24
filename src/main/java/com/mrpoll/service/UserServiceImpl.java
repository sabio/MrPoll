/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrpoll.service;

import com.mrpoll.dao.UserRepository;
import com.mrpoll.model.Role;
import com.mrpoll.model.User;
import java.util.List;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mrpoll.dao.UserDao;
import com.mrpoll.utils.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
    
    @Autowired
    private UserDao dao;
    
    @Autowired 
    private UserRepository userRepository;
    
    @Override
    public User findByUsername(String username) {
        User user = dao.findByUsername(username);
        User user2 = userRepository.findByUsername(username);
        
        if(user!=null){
            Hibernate.initialize(user.getRoles());
        }
        
        return user;
    }
    

    @Override
    public Role findRoleById(int roleId) {
        return dao.findRoleById(roleId);
    }

    @Override
    public Page<User> getUserListPage(Integer pageNumber, Integer pageSize) {
        return userRepository.findAll(new PageRequest(pageNumber, pageSize));
    }
}
