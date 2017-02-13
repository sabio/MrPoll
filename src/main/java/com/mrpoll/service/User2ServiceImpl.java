/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrpoll.service;

import com.mrpoll.dao.User2Dao;
import com.mrpoll.dao.UserRepository;
import com.mrpoll.model.Role;
import com.mrpoll.model.User2;
import java.util.List;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("user2Service")
@Transactional
public class User2ServiceImpl implements User2Service{
    
    @Autowired
    private User2Dao dao;
    
    @Autowired 
    private UserRepository userRepository;
    
    @Override
    public User2 findByUsername(String username) {
        User2 user = dao.findByUsername(username);
        User2 user2 = userRepository.findByUsername(username);
        
        if(user!=null){
            Hibernate.initialize(user.getRoles());
        }
        
        return user;
    }


    @Override
    public Role findRoleById(int roleId) {
        return dao.findRoleById(roleId);
    }
}
