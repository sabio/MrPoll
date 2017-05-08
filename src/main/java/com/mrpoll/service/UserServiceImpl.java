/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrpoll.service;

import com.mrpoll.model.FormUser;
import com.mrpoll.dao.UserRepository;
import com.mrpoll.model.User;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mrpoll.dao.UserDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public User findById(Integer id) {
        return userDao.findById(id);
    }
    
    @Override
    public User findByUsername(String username) {
        User user = userDao.findByUsername(username);
        User user2 = userRepository.findByUsername(username);
        /*
        if (user != null) {
            Hibernate.initialize(user.getRoles());
        }
        */

        return user;
    }
    
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    @Override
    public FormUser findFormUserById(Integer id) {
        return createFormUser(userDao.findById(id));
    }

    @Override
    public Page<User> getUserListPage(Integer pageNumber, Integer pageSize) {
        return userRepository.findAll(new PageRequest(pageNumber, pageSize));
    }

    private User createModelBean(FormUser formUser) {
        User model = new User();
        model.setId(formUser.getId());
        model.setEmail(formUser.getEmail());
        model.setName(formUser.getName());
        model.setUsername(formUser.getUsername());
        if(formUser.isNew() || userDao.passwordHasChanged(formUser.getId(), formUser.getPassword())){
            model.setPassword(passwordEncoder.encode(formUser.getPassword()));
        }
        else{
            model.setPassword(formUser.getPassword());
        }
        model.setEnabled(formUser.getEnabled());
        model.setRoles(formUser.getRoles());
        
        return model;
    }
    
    private FormUser createFormUser(User user){
        if(user == null) return null;
        
        FormUser formUser = new FormUser();
        formUser.setId(user.getId());
        formUser.setEmail(user.getEmail());
        formUser.setName(user.getName());
        formUser.setUsername(user.getUsername());
        formUser.setPassword(user.getPassword());
        formUser.setConfirmPassword(user.getPassword());
        formUser.setEnabled(user.getEnabled());
        formUser.setRoles(user.getRoles());
        
        return formUser;
    }

    @Override
    public void saveFormUser(FormUser formUser) {
        User user = createModelBean(formUser);
        userDao.save(user);
    }

    @Override
    public void updateFormUser(FormUser formUser) {
        User user = createModelBean(formUser);
        userDao.update(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        userDao.deleteUserById(id);
    }

}
